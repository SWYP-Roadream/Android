package com.yeogijeogi.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthInfoEntity(
    val accessToken: String,
    val refreshToken: String
)