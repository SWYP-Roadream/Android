package com.yeogijeogi.presentation.schedule.viewmodel

import androidx.lifecycle.ViewModel
import com.yeogijeogi.presentation.schedule.model.ScheduleEffect
import com.yeogijeogi.presentation.schedule.model.ScheduleEvent
import com.yeogijeogi.presentation.schedule.model.ScheduleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(

): ViewModel() {

    private val _state = MutableStateFlow(ScheduleState())
    val state = _state.asStateFlow()

    private val _effect = Channel<ScheduleEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: ScheduleEvent) {
        when(event) {
            else -> {}
        }
    }
}