package com.yeogijeogi.presentation.login.model

import com.yeogijeogi.domain.model.enums.LoginType

sealed interface LoginEffect {
    data class OnLogin(val type: LoginType): LoginEffect
    data object OnBoarding: LoginEffect
    data class OnNext(val screenType: OnBoardingScreenType): LoginEffect
    data class OnBack(val screenType: OnBoardingScreenType): LoginEffect
    data object GoMain: LoginEffect
}