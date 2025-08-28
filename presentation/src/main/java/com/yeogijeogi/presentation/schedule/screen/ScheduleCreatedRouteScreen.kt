package com.yeogijeogi.presentation.schedule.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetDefaults
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.yeogijeogi.presentation.component.FilterBadge
import com.yeogijeogi.presentation.schedule.component.PlaceDetailItem
import com.yeogijeogi.presentation.schedule.component.PlaceItem
import com.yeogijeogi.presentation.schedule.model.BottomSheetState
import com.yeogijeogi.presentation.schedule.model.schedulecreate.ScheduleCreateState
import com.yeogijeogi.presentation.schedule.viewmodel.ScheduleCreateViewModel
import com.yeogijeogi.presentation.theme.ui.theme.ArrowLeft
import com.yeogijeogi.presentation.theme.ui.theme.CaretDownOutline
import com.yeogijeogi.presentation.theme.ui.theme.EmptyLocation
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.SearchOutline
import com.yeogijeogi.presentation.theme.ui.theme.bodyMid16
import com.yeogijeogi.presentation.theme.ui.theme.bodyRegular14
import com.yeogijeogi.presentation.theme.ui.theme.bodySemiBold14
import com.yeogijeogi.presentation.theme.ui.theme.gray100
import com.yeogijeogi.presentation.theme.ui.theme.gray17
import com.yeogijeogi.presentation.theme.ui.theme.gray25
import com.yeogijeogi.presentation.theme.ui.theme.gray40
import com.yeogijeogi.presentation.theme.ui.theme.gray60
import com.yeogijeogi.presentation.theme.ui.theme.gray95

@Composable
fun ScheduleCreatedRouteScreenRoot(
    viewModel: ScheduleCreateViewModel,
    onClickSearch: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ScheduleCreatedRouteScreen(
        state = state,
        onClickSearch = onClickSearch
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleCreatedRouteScreen(modifier: Modifier = Modifier, state: ScheduleCreateState, onClickSearch: () -> Unit,) {
    val cameraPositionState = rememberCameraPositionState()
    val configuration = LocalConfiguration.current
    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.Expanded,
        skipHiddenState = false,
    )
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
         bottomSheetState = sheetState
    )

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = bottomSheetScaffoldState,
        sheetContainerColor = Color.White,
        sheetContent = {
            when(state.bottomSheetState){
                BottomSheetState.ROUTE -> {
                    Column(
                        modifier = Modifier
                            .navigationBarsPadding()
                    ) {
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

                BottomSheetState.SEARCH -> {
                    LazyColumn(
                        modifier = Modifier
                            .navigationBarsPadding()
                            .height(configuration.screenHeightDp.dp * 0.5f)
                    ) {
                        item {
                            Row(
                                modifier = Modifier.padding(horizontal = 20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "거리순",
                                    style = MaterialTheme.typography.bodyRegular14.copy(
                                        color = gray17
                                    )
                                )

                                Icon(
                                    imageVector = CaretDownOutline,
                                    contentDescription = null,
                                    tint = gray40
                                )
                            }

                            val tabs = listOf("음식점", "카페", "숙박", "반려동물동반", "시장 마트", "액티비티", "기타")
                            var selectedTabIndex by remember { mutableStateOf<Int?>(null) }
                            LazyRow(
                                modifier = Modifier,
                                contentPadding = PaddingValues(
                                    start = 20.dp,
                                    end = 20.dp,
                                    top = 16.dp,
                                    bottom = 8.dp
                                ),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(tabs.size) { index ->
                                    FilterBadge(
                                        text = tabs[index],
                                        isSelected = selectedTabIndex == index,
                                        onClick = { selectedTabIndex = index }
                                    )
                                }
                            }
                        }

                        items(5) {
                            PlaceItem(
                                modifier = Modifier.padding(vertical = 16.dp),
                                placeName = "장소 이름",
                                categoryName = "카테고리",
                                starRatio = "4.6",
                                starCount = 0,
                                address = "OO시 OO동",
                                onClick = {}
                            )
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 20.dp),
                                color = gray95
                            )
                        }
                    }
                }

                BottomSheetState.DETAIL -> {
                    PlaceDetailItem(
                        modifier = Modifier.navigationBarsPadding(),
                        placeName = "장소 이름",
                        categoryName = "카테고리",
                        starRatio = "4.6",
                        reviewCount = 3,
                        address = "도시이름 OO구"
                    )
                }
            }
        }
        ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            GoogleMap(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.LightGray),
                cameraPositionState = cameraPositionState,
                uiSettings = MapUiSettings(
                    zoomControlsEnabled = false
                )
            )

            when(state.bottomSheetState){
                BottomSheetState.ROUTE -> {
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
                else -> {
                    Surface(
                        shadowElevation = 4.dp
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = gray100)
                                .statusBarsPadding()
                                .height(54.dp)
                                .padding(horizontal = 20.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Icon(
                                imageVector = ArrowLeft,
                                contentDescription = null,
                                tint = gray25
                            )

                            Text(
                                modifier = Modifier.weight(1f),
                                text = state.search,
                                style = MaterialTheme.typography.bodyMid16.copy(
                                    color = gray17
                                )
                            )

                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = null,
                                tint = gray60
                            )
                        }
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
            state = ScheduleCreateState(),
            onClickSearch = {}
        )
    }
}