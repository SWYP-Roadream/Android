package com.yeogijeogi.domain.database

import com.yeogijeogi.domain.model.data.AuthInfo
import kotlinx.coroutines.flow.Flow

interface SessionStorage {
    suspend fun getToken(): Flow<AuthInfo?>
    suspend fun setToken(info: AuthInfo?)
}