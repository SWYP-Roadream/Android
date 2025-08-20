package com.yeogijeogi.domain.model.data

import com.yeogijeogi.domain.model.enums.Age
import com.yeogijeogi.domain.model.enums.CompanionType
import com.yeogijeogi.domain.model.enums.LoginType
import com.yeogijeogi.domain.model.enums.Mbti

data class SignUp(
    val provider: LoginType,
    val providerUserId: String,
    val imageUrl: String?,
    val nickname: String,
    val ageGroup: Age,
    val companions: List<CompanionType>,
    val mbti: Mbti?,
)
