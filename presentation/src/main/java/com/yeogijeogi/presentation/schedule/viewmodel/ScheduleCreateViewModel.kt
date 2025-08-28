package com.yeogijeogi.presentation.schedule.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeogijeogi.presentation.schedule.model.schedulecreate.ScheduleCreateEffect
import com.yeogijeogi.presentation.schedule.model.schedulecreate.ScheduleCreateEvent
import com.yeogijeogi.presentation.schedule.model.schedulecreate.ScheduleCreateState
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
class ScheduleCreateViewModel @Inject constructor(

): ViewModel() {

    private val _state = MutableStateFlow(ScheduleCreateState())
    val state = _state.asStateFlow()

    private val _effect = Channel<ScheduleCreateEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: ScheduleCreateEvent) {
        when(event) {
            is ScheduleCreateEvent.OnChangeSearch -> {
                viewModelScope.launch {
                    Timber.e("search ${event.search}")
                    _state.update { it.copy(search = event.search) }
                }
            }

            is ScheduleCreateEvent.OnSearch -> {
                if(state.value.search.isEmpty()) return
                viewModelScope.launch {
                    _state.update { it.copy(bottomSheetState = event.bottomSheetState) }
                    _effect.send(ScheduleCreateEffect.OnBack)
                }
            }
        }
    }
}