package com.yeogijeogi.presentation.schedule.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yeogijeogi.presentation.component.RoundButton
import com.yeogijeogi.presentation.schedule.viewmodel.ScheduleViewModel
import com.yeogijeogi.presentation.theme.ui.theme.CalenderOutline
import com.yeogijeogi.presentation.theme.ui.theme.CaretDownOutline
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.background
import com.yeogijeogi.presentation.theme.ui.theme.bodyMid16
import com.yeogijeogi.presentation.theme.ui.theme.bodySemiBold14
import com.yeogijeogi.presentation.theme.ui.theme.gray17
import com.yeogijeogi.presentation.theme.ui.theme.gray30
import com.yeogijeogi.presentation.theme.ui.theme.gray40
import com.yeogijeogi.presentation.theme.ui.theme.gray80
import com.yeogijeogi.presentation.theme.ui.theme.gray97
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun ScheduleDateScreenRoot(
    viewModel: ScheduleViewModel = hiltViewModel(),
    onNavigateTitle: () -> Unit,
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val state by viewModel.state.collectAsStateWithLifecycle()

    ScheduleDateScreen(
        onClickDate = {
            showDatePicker = true
        },
        onNavigateTitle = onNavigateTitle
    )

    if (showDatePicker) {
        DateRangePickerModal(
            onDateRangeSelected = {},
            onDismiss = { showDatePicker = false }
        )
    }
}

@Composable
fun ScheduleDateScreen(
    modifier: Modifier = Modifier,
    onClickDate: () -> Unit,
    onNavigateTitle: () -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            Box(
                modifier = Modifier.statusBarsPadding()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = null
                    )
                }
                HorizontalDivider(modifier = Modifier.align(Alignment.BottomCenter), color = gray97)
            }
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 24.dp)
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "언제 떠날까요?",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = gray17
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "날짜",
                style = MaterialTheme.typography.bodySemiBold14.copy(
                    color = gray30
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = background)
                    .clickable(onClick = onClickDate)
                    .padding(8.dp)
                    .height(32.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = CalenderOutline,
                    contentDescription = null,
                    tint = gray80
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = "2025.12.12 - 2025.12.12",
                    style = MaterialTheme.typography.bodyMid16.copy(
                        color = gray80
                    )
                )
                Icon(
                    imageVector = CaretDownOutline,
                    contentDescription = null,
                    tint = gray40
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            RoundButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                text = "다음",
                onClick = onNavigateTitle
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
    onDismiss: () -> Unit
) {
    val dateRangePickerState = rememberDateRangePickerState()
//    val formatter = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateRangeSelected(
                        Pair(
                            dateRangePickerState.selectedStartDateMillis,
                            dateRangePickerState.selectedEndDateMillis
                        )
                    )
                    onDismiss()
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DateRangePicker(
            state = dateRangePickerState,
            headline = {
                val startDate = formatDate(dateRangePickerState.selectedStartDateMillis)
                val endDate = formatDate(dateRangePickerState.selectedEndDateMillis)
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    text = "$startDate - $endDate",
                    style = MaterialTheme.typography.titleMedium.copy(
                        textAlign = TextAlign.Center
                    ),
                )
            },
            showModeToggle = false,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(16.dp)
        )
    }
}

fun formatDate(millis: Long?): String {
    if (millis == null) return ""

    val localDate = Instant.ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault())   // 원하는 타임존 적용
        .toLocalDate()

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // 원하는 패턴 지정
    return localDate.format(formatter)
}

@Preview
@Composable
private fun ScheduleCreateScreenPreview() {
    RoadreamTheme {
        ScheduleDateScreen(
            onClickDate = {},
            onNavigateTitle = {},
        )
    }
}