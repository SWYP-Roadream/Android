package com.yeogijeogi.roadream

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeogijeogi.domain.database.SessionStorage
import com.yeogijeogi.roadream.model.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sessionStorage: SessionStorage
): ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isCheckingAuth = true) }
            _state.update { it.copy(isLoggedIn = sessionStorage.getToken().first() != null) }
            _state.update { it.copy(isCheckingAuth = false) }
        }
    }
}