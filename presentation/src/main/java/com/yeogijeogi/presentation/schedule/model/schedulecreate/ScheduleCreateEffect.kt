package com.yeogijeogi.presentation.schedule.model.schedulecreate

sealed interface ScheduleCreateEffect {
    data object OnBack: ScheduleCreateEffect
}