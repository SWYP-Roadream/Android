package com.yeogijeogi.domain.usecase

import com.yeogijeogi.domain.model.data.Login
import com.yeogijeogi.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(login: Login) = repository.postLogin(login)
}