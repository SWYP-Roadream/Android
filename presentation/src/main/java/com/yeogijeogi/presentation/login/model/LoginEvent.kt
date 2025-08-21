package com.yeogijeogi.presentation.login.model

import com.yeogijeogi.domain.model.enums.Age
import com.yeogijeogi.domain.model.enums.CompanionType
import com.yeogijeogi.domain.model.enums.Gender
import com.yeogijeogi.domain.model.enums.LoginType
import com.yeogijeogi.domain.model.enums.Mbti
import com.yeogijeogi.presentation.model.UserData

sealed interface LoginEvent {
    data class OnClickLoginImage(val type: LoginType): LoginEvent
    data class OnLogin(val user: UserData?, val type: LoginType): LoginEvent
    data class ChangeNickname(val nickname: String): LoginEvent
    data class SelectedGender(val gender: Gender?): LoginEvent
    data class SelectedAge(val age: Age?): LoginEvent
    data class SelectedCompanion(val companion: CompanionType): LoginEvent
    data class SelectedMbti(val mbti: Mbti): LoginEvent
    data class OnClickSkip(val screenType: OnBoardingScreenType): LoginEvent
    data class OnNext(val screenType: OnBoardingScreenType): LoginEvent
    data class OnBack(val screenType: OnBoardingScreenType): LoginEvent
    data object SignUp: LoginEvent
}