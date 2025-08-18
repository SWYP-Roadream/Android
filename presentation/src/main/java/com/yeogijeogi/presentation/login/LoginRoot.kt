package com.yeogijeogi.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yeogijeogi.presentation.R
import com.yeogijeogi.presentation.login.model.LoginEffect
import com.yeogijeogi.presentation.login.model.LoginEvent
import com.yeogijeogi.presentation.login.model.LoginType
import com.yeogijeogi.presentation.login.util.AuthClient
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.util.ObserveAsEvents
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun LoginRoot(
    viewModel: LoginViewModel = hiltViewModel(),
    onBoarding: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val authClient by remember {
        mutableStateOf(
            AuthClient(
                context = context,
            )
        )
    }

    ObserveAsEvents(viewModel.effect) { effect ->
        when (effect) {
            is LoginEffect.OnLogin -> {
                scope.launch {
                    val signIn = async { authClient.getSignIn(effect.type) }.await()
                    Timber.e("signIn ${signIn.user} ${signIn.errorMessage}")
                    signIn.user?.let {
                        viewModel.onEvent(LoginEvent.OnBoarding(signIn.user))
                    }
                }
            }

            is LoginEffect.OnBoarding -> onBoarding()
        }
    }

    LoginScreen(
        onEvent = viewModel::onEvent
    )
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onEvent: (LoginEvent) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 45.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "여행자들을 위한 진정한 로드맵 '여기저기'"
                )

                Image(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Text(text = "간편 로그인")
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onEvent(LoginEvent.OnLogin(LoginType.KAKAO)) },
                    painter = painterResource(R.drawable.kakao_login_img),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onEvent(LoginEvent.OnLogin(LoginType.GOOGLE)) },
                    painter = painterResource(R.drawable.google_login_img),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginRoutePreview() {
    RoadreamTheme {
        LoginRoot(
            onBoarding = {}
        )
    }
}