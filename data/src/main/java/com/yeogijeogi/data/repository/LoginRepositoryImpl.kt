package com.yeogijeogi.data.repository

import com.yeogijeogi.data.mapper.toRequestBody
import com.yeogijeogi.data.service.LoginService
import com.yeogijeogi.domain.model.data.Login
import com.yeogijeogi.domain.model.data.SignUp
import com.yeogijeogi.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService
) : LoginRepository {
    override suspend fun postSignUp(signUp: SignUp): Flow<String> = flow {
        runCatching {
            loginService.postSignUp(signUp.toRequestBody())
        }
            .onSuccess {
                emit(signUp.providerUserId)
            }
            .onFailure { throw it }
    }

    override suspend fun postLogin(login: Login): Result<Unit> = runCatching {
        loginService.login(login.toRequestBody())
    }
}