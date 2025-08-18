package com.yeogijeogi.presentation.model

data class SignIn(
    val user: UserData?,
    val errorMessage: String?
)

data class UserData(
    val token: String?,
    val username: String?,
    val email: String?,
    val urlProfile: String?
)
