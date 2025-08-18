package com.yeogijeogi.presentation.login.model

import com.yeogijeogi.presentation.model.UserData

sealed interface LoginEvent {
    data class OnLogin(val type: LoginType): LoginEvent
    data class OnBoarding(val user: UserData?): LoginEvent
}