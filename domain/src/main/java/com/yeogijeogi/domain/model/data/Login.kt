package com.yeogijeogi.domain.model.data

import com.yeogijeogi.domain.model.enums.LoginType

data class Login(
    val provider: LoginType,
    val providerUserId: String,
)
