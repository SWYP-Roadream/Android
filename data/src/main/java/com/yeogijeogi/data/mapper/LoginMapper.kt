package com.yeogijeogi.data.mapper

import com.yeogijeogi.data.model.SignUpRequest
import com.yeogijeogi.domain.model.data.SignUp
import okhttp3.RequestBody

fun SignUp.toRequestBody(): RequestBody {
    return SignUpRequest(
        provider = provider.name,
        providerUserId = providerUserId,
        imageUrl = imageUrl,
        nickname = nickname,
        ageGroup = ageGroup.name,
        companions = companions.map { it.name },
        mbti = mbti?.name
    ).toRequestBody()
}