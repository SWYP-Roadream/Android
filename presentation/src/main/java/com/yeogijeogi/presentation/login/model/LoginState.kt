package com.yeogijeogi.presentation.login.model

import com.yeogijeogi.presentation.model.UserData

data class LoginState(
    val user: UserData? = null,
    val loginType: LoginType? = null,
)
