package com.yeogijeogi.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeogijeogi.presentation.login.model.LoginEffect
import com.yeogijeogi.presentation.login.model.LoginEvent
import com.yeogijeogi.presentation.login.model.LoginState
import com.yeogijeogi.presentation.login.model.OnBoardingScreenType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _effect = Channel<LoginEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnLogin -> {
                viewModelScope.launch {
                    _state.update { it.copy(loginType = event.type) }
                    _effect.send(LoginEffect.OnLogin(event.type))
                }
            }

            is LoginEvent.OnBoarding -> {
                viewModelScope.launch {
                    _state.update { it.copy(user = event.user) }
                    _effect.send(LoginEffect.OnBoarding)
                }
            }

            is LoginEvent.ChangeMbti -> viewModelScope.launch {
                _state.update { it.copy(mbti = event.mbti) }
            }

            is LoginEvent.ChangeNickname -> viewModelScope.launch {
                _state.update { it.copy(nickname = event.nickname) }
            }

            is LoginEvent.OnClickSkip -> viewModelScope.launch {
                _state.update {
                    when (event.screenType) {
                        OnBoardingScreenType.COMPANION -> {
                            it.copy(companions = listOf())
                        }

                        OnBoardingScreenType.MBTI -> it.copy(mbti = "")
                        else -> it
                    }
                }
                _effect.send(LoginEffect.OnNext(event.screenType))
            }

            is LoginEvent.SelectedAge -> viewModelScope.launch {
                _state.update { it.copy(age = event.age) }
            }

            is LoginEvent.SelectedCompanion -> viewModelScope.launch {
                val list =
                    if (state.value.companions.contains(event.companion)) state.value.companions - event.companion else state.value.companions + event.companion
                _state.update { it.copy(companions = list.takeLast(3)) }
            }

            is LoginEvent.SelectedGender -> viewModelScope.launch {
                _state.update { it.copy(gender = event.gender) }
            }

            is LoginEvent.OnNext -> viewModelScope.launch {
                when(event.screenType) {
                    OnBoardingScreenType.MBTI -> {
                        if(!state.value.isCheck) {
                            Timber.e("isCheck is false")
                            return@launch
                        }
                    }
                    else -> Unit
                }
                _effect.send(LoginEffect.OnNext(event.screenType))
            }

            is LoginEvent.OnBack -> viewModelScope.launch {
                _effect.send(LoginEffect.OnBack(event.screenType))
            }

            is LoginEvent.SignUp -> signUp()
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            _effect.send(LoginEffect.GoMain)
        }
    }
}