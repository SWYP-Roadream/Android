package com.yeogijeogi.domain.repository

import com.yeogijeogi.domain.model.data.SignUp
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun postSignUp(signUp: SignUp): Flow<String>
}