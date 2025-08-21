package com.yeogijeogi.presentation.login.model

import com.yeogijeogi.domain.model.enums.Age
import com.yeogijeogi.domain.model.enums.CompanionType
import com.yeogijeogi.domain.model.enums.Gender
import com.yeogijeogi.domain.model.enums.LoginType
import com.yeogijeogi.domain.model.enums.Mbti
import com.yeogijeogi.presentation.model.UserData

data class LoginState(
    val user: UserData? = null,
    val loginType: LoginType? = null,
    val nickname: String = "",
    val gender: Gender? = null,
    val age: Age? = null,
    val companions: List<CompanionType> = listOf(),
    val mbti: Mbti? = null
) {
    val isCheck : Boolean
        get() = user != null && loginType != null && nickname.isNotEmpty() && gender != null && age != null
}
