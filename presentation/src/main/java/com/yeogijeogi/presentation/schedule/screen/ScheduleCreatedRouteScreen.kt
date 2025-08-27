package com.yeogijeogi.presentation.schedule.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.yeogijeogi.presentation.theme.ui.theme.ArrowLeft
import com.yeogijeogi.presentation.theme.ui.theme.EmptyLocation
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.SearchOutline
import com.yeogijeogi.presentation.theme.ui.theme.bodyMid16
import com.yeogijeogi.presentation.theme.ui.theme.bodySemiBold14
import com.yeogijeogi.presentation.theme.ui.theme.gray100
import com.yeogijeogi.presentation.theme.ui.theme.gray17
import com.yeogijeogi.presentation.theme.ui.theme.gray25
import com.yeogijeogi.presentation.theme.ui.theme.gray60
import com.yeogijeogi.presentation.theme.ui.theme.gray95

@Composable
fun ScheduleCreatedRouteScreenRoot(
    onClickSearch: () -> Unit,
) {
    ScheduleCreatedRouteScreen(
        onClickSearch = onClickSearch
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleCreatedRouteScreen(modifier: Modifier = Modifier, onClickSearch: () -> Unit,) {
    val cameraPositionState = rememberCameraPositionState()
    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.Expanded,
        skipHiddenState = false
    )
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
         bottomSheetState = sheetState
    )

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = bottomSheetScaffoldState,
        sheetContainerColor = gray100,
        sheetContent = {
            Column(
                modifier = Modifier.navigationBarsPadding()
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Image(
                        imageVector = EmptyLocation,
                        contentDescription = null,
                    )
                    Text(
                        text = "장소를 검색해 추가해보세요!",
                        style = MaterialTheme.typography.bodyMid16.copy(
                            color = gray60
                        )
                    )
                }
                HorizontalDivider(color = gray95)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "1일차",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = gray17
                        )
                    )

                    OutlinedButton(
                        onClick = {},
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(width = 1.dp, color = gray95)
                    ) {
                        Text(
                            text = "저장하기",
                            style = MaterialTheme.typography.bodySemiBold14.copy(
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
            }
        }
        ) { paddingValues ->
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            GoogleMap(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(color = Color.LightGray),
                cameraPositionState = cameraPositionState,
                uiSettings = MapUiSettings(
                    zoomControlsEnabled = false
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 20.dp, vertical = 7.dp)
            ) {
                Surface(
                    shadowElevation = 4.dp,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = ArrowLeft,
                            contentDescription = null,
                            tint = gray25
                        )
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Surface(
                    shadowElevation = 4.dp,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = onClickSearch)
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "장소를 검색해보세요!",
                            style = MaterialTheme.typography.bodyMid16.copy(
                                color = gray60
                            )
                        )

                        Icon(
                            imageVector = SearchOutline,
                            contentDescription = null,
                            tint = gray60
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ScheduleCreatedRouteScreenPreview() {
    RoadreamTheme {
        ScheduleCreatedRouteScreen(
            onClickSearch = {}
        )
    }
}