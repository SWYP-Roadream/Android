package com.yeogijeogi.presentation.schedule.model.schedulecreate

import com.yeogijeogi.presentation.schedule.model.BottomSheetState

sealed interface ScheduleCreateEvent {
    data class OnChangeSearch(val search: String) : ScheduleCreateEvent
    data class OnSearch(val bottomSheetState: BottomSheetState): ScheduleCreateEvent
}