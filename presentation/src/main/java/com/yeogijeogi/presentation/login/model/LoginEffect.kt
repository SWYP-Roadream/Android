package com.yeogijeogi.presentation.login.model

sealed interface LoginEffect {
    data class OnLogin(val type: LoginType): LoginEffect
    data object OnBoarding: LoginEffect
}