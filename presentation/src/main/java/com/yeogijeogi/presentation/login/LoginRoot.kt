package com.yeogijeogi.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yeogijeogi.domain.model.enums.LoginType
import com.yeogijeogi.presentation.R
import com.yeogijeogi.presentation.login.model.LoginEffect
import com.yeogijeogi.presentation.login.model.LoginEvent
import com.yeogijeogi.presentation.login.util.AuthClient
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.bodyMid18
import com.yeogijeogi.presentation.theme.ui.theme.gray100
import com.yeogijeogi.presentation.util.ObserveAsEvents
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun LoginRoot(
    viewModel: LoginViewModel = hiltViewModel(),
    onBoarding: () -> Unit,
    goMain: () -> Unit,
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
                    signIn.user?.let { user ->
                        viewModel.onEvent(LoginEvent.OnLogin(user, effect.type))
                    }
                }
            }

            is LoginEffect.OnBoarding -> onBoarding()

            is LoginEffect.GoMain -> goMain()
            else -> Unit
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
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.login_background),
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )

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
                    text = stringResource(R.string.login_title),
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = gray100
                    )
                )

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Outlined.MailOutline,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Text(
                    text = stringResource(R.string.oauth_login_title),
                    style = MaterialTheme.typography.bodyMid18.copy(color = gray100)
                )
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onEvent(LoginEvent.OnClickLoginImage(LoginType.KAKAO)) },
                    painter = painterResource(R.drawable.kakao_login_img),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onEvent(LoginEvent.OnClickLoginImage(LoginType.GOOGLE)) },
                    painter = painterResource(R.drawable.google_login_img_3x),
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
            onBoarding = {},
            goMain = {}
        )
    }
}