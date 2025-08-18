package com.yeogijeogi.presentation.login.util

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.yeogijeogi.presentation.BuildConfig
import com.yeogijeogi.presentation.login.model.LoginType
import com.yeogijeogi.presentation.model.SignIn
import com.yeogijeogi.presentation.model.UserData
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import kotlin.coroutines.resume

class AuthClient(
    private val context: Context,
) {
    private val credentialManager = CredentialManager.create(context)
    private val firebaseAuth = FirebaseAuth.getInstance()

    suspend fun getSignIn(loginType: LoginType): SignIn {
        return when (loginType) {
            LoginType.KAKAO -> signInKakao()
            LoginType.GOOGLE -> signInGoogle()
        }
    }

    private suspend fun signInKakao(): SignIn = suspendCancellableCoroutine { cont ->
        fun resumeOnce(value: SignIn) {
            if (cont.isActive) cont.resume(value)
        }

        // 사용자 정보까지 받아서 SignIn으로 만들어 주는 헬퍼
        fun fetchUserAndResume() {
            UserApiClient.instance.me { user, e ->
                if (e != null) {
                    resumeOnce(SignIn(user = null, errorMessage = e.message ?: "사용자 정보 요청 실패"))
                } else if (user != null) {
                    resumeOnce(
                        SignIn(
                            user = UserData(
                                token = "${user.id}",
                                username = user.kakaoAccount?.profile?.nickname,
                                email = user.kakaoAccount?.email,
                                urlProfile = user.kakaoAccount?.profile?.thumbnailImageUrl
                            ),
                            errorMessage = null
                        )
                    )
                } else {
                    resumeOnce(SignIn(user = null, errorMessage = "알 수 없는 사용자 정보"))
                }
            }
        }

        val accountCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                resumeOnce(SignIn(user = null, errorMessage = error.message ?: "카카오계정 로그인 실패"))
            } else if (token != null) {
                // 계정 로그인 성공 → 사용자 정보 조회
                fetchUserAndResume()
            } else {
                resumeOnce(SignIn(user = null, errorMessage = "카카오계정 로그인 결과 없음"))
            }
        }

        // 1) 카카오톡 앱 로그인 우선
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    // 사용자가 취소한 경우는 종료
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        resumeOnce(SignIn(user = null, errorMessage = "사용자 취소"))
                        return@loginWithKakaoTalk
                    }
                    // 카톡 실패 → 계정 로그인으로 폴백
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = accountCallback)
                } else if (token != null) {
                    // 카톡 로그인 성공 → 사용자 정보 조회
                    fetchUserAndResume()
                } else {
                    // 토큰이 없는 희귀 케이스 → 계정 로그인으로 폴백
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = accountCallback)
                }
            }
        } else {
            // 2) 카카오톡 미설치 → 계정 로그인
            UserApiClient.instance.loginWithKakaoAccount(context, callback = accountCallback)
        }

        // 취소 처리(특별히 취소 API는 없어서 로그만)
        cont.invokeOnCancellation {
            // Kakao SDK에 취소 호출 제공 안됨. 필요시 내부 플래그로 무시 처리만.
        }
    }

    private suspend fun signInGoogle(): SignIn {
        return try {
            val result = buildCredentialResponse()
            handleSignIn(result)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignIn(
                user = null,
                errorMessage = "error $e"
            )
        }
    }

    private suspend fun handleSignIn(result: GetCredentialResponse): SignIn {
        val credential = result.credential
        return if (
            credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) {
            try {
                val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val authCredential =
                    GoogleAuthProvider.getCredential(tokenCredential.idToken, null)

                val user = firebaseAuth.signInWithCredential(authCredential).await().user
                user?.run {
                    SignIn(
                        user = UserData(
                            token = uid,
                            username = displayName,
                            email = email,
                            urlProfile = photoUrl.toString()
                        ),
                        errorMessage = null
                    )
                } ?: SignIn(
                    user = null,
                    errorMessage = "not User"
                )

            } catch (e: GoogleIdTokenParsingException) {
                SignIn(
                    user = null,
                    errorMessage = "not User"
                )
            }
        } else {
            SignIn(
                user = null,
                errorMessage = "credential is not GoogleIdTokenCredential"
            )
        }
    }

    private suspend fun buildCredentialResponse(): GetCredentialResponse {
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption
                    .Builder()
                    .setFilterByAuthorizedAccounts(true)
                    .setServerClientId(BuildConfig.ClientId)
                    .setAutoSelectEnabled(false)
                    .build()
            )
            .build()

        return credentialManager.getCredential(
            context = context, request = request
        )
    }
}