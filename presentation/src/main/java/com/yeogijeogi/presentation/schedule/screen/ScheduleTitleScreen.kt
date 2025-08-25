package com.yeogijeogi.presentation.schedule.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme

@Composable
fun ScheduleTitleScreenRoot() {
    ScheduleTitleScreen()
}

@Composable
fun ScheduleTitleScreen(modifier: Modifier = Modifier) {

}

@Preview
@Composable
private fun ScheduleCreateScreenPreview() {
    RoadreamTheme {
        ScheduleTitleScreen()
    }
}