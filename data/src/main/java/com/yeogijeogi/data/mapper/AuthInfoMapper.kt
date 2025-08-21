package com.yeogijeogi.data.mapper

import com.yeogijeogi.data.model.AuthInfoEntity
import com.yeogijeogi.domain.model.data.AuthInfo

fun AuthInfo.toEntity(): AuthInfoEntity {
    return AuthInfoEntity(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}

fun AuthInfoEntity.toDomain(): AuthInfo {
    return AuthInfo(
        accessToken, refreshToken
    )
}