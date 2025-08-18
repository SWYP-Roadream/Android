package com.yeogijeogi.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeogijeogi.presentation.login.model.LoginEffect
import com.yeogijeogi.presentation.login.model.LoginEvent
import com.yeogijeogi.presentation.login.model.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
        }
    }
}