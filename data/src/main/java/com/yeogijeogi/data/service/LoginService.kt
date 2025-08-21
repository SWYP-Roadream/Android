package com.yeogijeogi.data.service

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/user/login")
    suspend fun postSignUp(
        @Body requestBody: RequestBody
    )

    @POST("/user/login")
    suspend fun login(
        @Body requestBody: RequestBody
    )
}