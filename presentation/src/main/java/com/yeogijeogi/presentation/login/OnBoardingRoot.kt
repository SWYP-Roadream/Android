package com.yeogijeogi.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yeogijeogi.presentation.R
import com.yeogijeogi.presentation.login.component.AgeField
import com.yeogijeogi.presentation.login.component.CompanionField
import com.yeogijeogi.presentation.login.component.GenderField
import com.yeogijeogi.presentation.login.component.MbtiField
import com.yeogijeogi.presentation.login.component.NicknameTextField
import com.yeogijeogi.presentation.login.component.OnBoardingBase
import com.yeogijeogi.presentation.login.component.StepProgressBar
import com.yeogijeogi.presentation.login.model.LoginEffect
import com.yeogijeogi.presentation.login.model.LoginEvent
import com.yeogijeogi.presentation.login.model.LoginState
import com.yeogijeogi.presentation.login.model.OnBoardingScreenType
import com.yeogijeogi.presentation.login.model.OnboardingType
import com.yeogijeogi.presentation.util.ObserveAsEvents
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OnBoardingRoot(
    viewModel: LoginViewModel = hiltViewModel(),
    onBack: () -> Unit,
    goMain: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    var onBoardingType by remember {
        mutableStateOf(OnboardingType.START)
    }
    val pagerState = rememberPagerState { OnBoardingScreenType.entries.size }

    ObserveAsEvents(viewModel.effect) { effect ->
        when (effect) {
            is LoginEffect.OnNext -> {
                when (effect.screenType) {
                    OnBoardingScreenType.MBTI -> onBoardingType = OnboardingType.END
                    else -> scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }

            is LoginEffect.OnBack -> {
                when (effect.screenType) {
                    OnBoardingScreenType.NICKNAME -> onBack()
                    else -> scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }
            }

            is LoginEffect.GoMain -> goMain()

            else -> Unit
        }
    }

    LaunchedEffect(onBoardingType) {
        when (onBoardingType) {
            OnboardingType.START -> {
                delay(2000L)
                onBoardingType = OnboardingType.PROGRESSED
            }

            OnboardingType.END -> {
                delay(500L)
                viewModel.onEvent(LoginEvent.SignUp)
            }
            else -> Unit
        }
    }

    when (onBoardingType) {
        OnboardingType.START -> OnBoardingSplash()
        OnboardingType.PROGRESSED -> UserInfoScreen(
            pagerState = pagerState,
            state = state,
            onEvent = viewModel::onEvent
        )

        OnboardingType.END -> OnBoardingSplash()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoScreen(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onEvent(LoginEvent.OnBack(OnBoardingScreenType.entries[pagerState.currentPage])) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    StepProgressBar(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        progress = pagerState.currentPage.toFloat() / (pagerState.pageCount - 1)
                    )
                }
            )
        }
    ) { innerPadding ->
        HorizontalPager(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            state = pagerState,
            userScrollEnabled = false
        ) { index ->
            val onBoardingType = OnBoardingScreenType.entries[index]
            OnBoardingBase(
                title = getOnBoardingTitle(onBoardingType),
                content = {
                    when (onBoardingType) {
                        OnBoardingScreenType.NICKNAME -> {
                            NicknameTextField(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 20.dp),
                                value = state.nickname,
                                onChangeValue = { onEvent(LoginEvent.ChangeNickname(it)) }
                            )
                        }

                        OnBoardingScreenType.GENDER -> {
                            GenderField(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(top = 55.dp)
                                    .padding(horizontal = 20.dp),
                                selected = state.gender,
                                onClick = { onEvent(LoginEvent.SelectedGender(it)) }
                            )
                        }

                        OnBoardingScreenType.AGE -> AgeField(
                            modifier = Modifier
                                .weight(1f)
                                .padding(top = 20.dp)
                                .padding(horizontal = 20.dp),
                            selected = state.age,
                            onClick = { onEvent(LoginEvent.SelectedAge(it)) }
                        )

                        OnBoardingScreenType.COMPANION -> CompanionField(
                            modifier = Modifier
                                .weight(1f)
                                .padding(top = 20.dp)
                                .padding(horizontal = 20.dp),
                            selected = state.companions,
                            onClick = { onEvent(LoginEvent.SelectedCompanion(it)) },
                            onClickSkip = { onEvent(LoginEvent.OnClickSkip(onBoardingType)) }
                        )

                        OnBoardingScreenType.MBTI -> MbtiField(
                            modifier = Modifier
                                .weight(1f)
                                .padding(top = 20.dp)
                                .padding(horizontal = 20.dp),
                            value = state.mbti,
                            onChangeValue = { onEvent(LoginEvent.ChangeMbti(it)) },
                            onClickSkip = { onEvent(LoginEvent.OnClickSkip(onBoardingType)) }
                        )
                    }
                },
                onClick = { onEvent(LoginEvent.OnNext(onBoardingType)) }
            )
        }
    }
}

@Composable
private fun getOnBoardingTitle(screenType: OnBoardingScreenType): String {
    return when(screenType) {
        OnBoardingScreenType.NICKNAME -> stringResource(R.string.nicknameTitle)
        OnBoardingScreenType.GENDER -> stringResource(R.string.genderTitle)
        OnBoardingScreenType.AGE -> stringResource(R.string.ageTitle)
        OnBoardingScreenType.COMPANION -> stringResource(R.string.companionTitle)
        OnBoardingScreenType.MBTI -> stringResource(R.string.mbtiTitle)
    }
}

@Composable
fun OnBoardingSplash(modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(2f))

            Column(
                modifier = Modifier.weight(8f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "여행자들을 위한\n진정한 로드맵\n'여기저기'",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold
                )

                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .background(color = Color.Gray)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.size(48.dp),
                        imageVector = Icons.Default.Email,
                        contentDescription = null
                    )
                }
            }
        }
    }
}