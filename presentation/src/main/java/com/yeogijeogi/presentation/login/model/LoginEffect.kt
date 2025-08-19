package com.yeogijeogi.presentation.login.model

sealed interface LoginEffect {
    data class OnLogin(val type: LoginType): LoginEffect
    data object OnBoarding: LoginEffect
    data class OnNext(val screenType: OnBoardingScreenType): LoginEffect
    data class OnBack(val screenType: OnBoardingScreenType): LoginEffect
    data object GoMain: LoginEffect
}