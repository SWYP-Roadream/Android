package com.yeogijeogi.presentation.login.model

import com.yeogijeogi.domain.enums.Age
import com.yeogijeogi.domain.enums.CompanionType
import com.yeogijeogi.domain.enums.Gender
import com.yeogijeogi.presentation.model.UserData

sealed interface LoginEvent {
    data class OnLogin(val type: LoginType): LoginEvent
    data class OnBoarding(val user: UserData?): LoginEvent
    data class ChangeNickname(val nickname: String): LoginEvent
    data class SelectedGender(val gender: Gender?): LoginEvent
    data class SelectedAge(val age: Age?): LoginEvent
    data class SelectedCompanion(val companion: CompanionType): LoginEvent
    data class ChangeMbti(val mbti: String): LoginEvent
    data class OnClickSkip(val screenType: OnBoardingScreenType): LoginEvent
    data class OnNext(val screenType: OnBoardingScreenType): LoginEvent
    data class OnBack(val screenType: OnBoardingScreenType): LoginEvent
    data object SignUp: LoginEvent
}