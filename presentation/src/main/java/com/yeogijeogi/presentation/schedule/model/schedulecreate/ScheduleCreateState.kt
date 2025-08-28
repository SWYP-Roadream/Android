package com.yeogijeogi.presentation.schedule.model.schedulecreate

import com.yeogijeogi.presentation.schedule.model.BottomSheetState

data class ScheduleCreateState(
    val search: String = "",
    val bottomSheetState: BottomSheetState = BottomSheetState.ROUTE,
)
