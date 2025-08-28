package com.yeogijeogi.presentation.schedule.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yeogijeogi.presentation.component.FilterBadge
import com.yeogijeogi.presentation.schedule.model.BottomSheetState
import com.yeogijeogi.presentation.schedule.model.schedulecreate.ScheduleCreateEffect
import com.yeogijeogi.presentation.schedule.model.schedulecreate.ScheduleCreateEvent
import com.yeogijeogi.presentation.schedule.model.schedulecreate.ScheduleCreateState
import com.yeogijeogi.presentation.schedule.viewmodel.ScheduleCreateViewModel
import com.yeogijeogi.presentation.theme.ui.theme.ArrowLeft
import com.yeogijeogi.presentation.theme.ui.theme.Delete
import com.yeogijeogi.presentation.theme.ui.theme.Location
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.SearchOutline
import com.yeogijeogi.presentation.theme.ui.theme.bodyMid16
import com.yeogijeogi.presentation.theme.ui.theme.bodySemiBold12
import com.yeogijeogi.presentation.theme.ui.theme.bodySemiBold16
import com.yeogijeogi.presentation.theme.ui.theme.captionRegular12
import com.yeogijeogi.presentation.theme.ui.theme.gray17
import com.yeogijeogi.presentation.theme.ui.theme.gray25
import com.yeogijeogi.presentation.theme.ui.theme.gray60
import com.yeogijeogi.presentation.theme.ui.theme.gray95
import com.yeogijeogi.presentation.util.ObserveAsEvents
import timber.log.Timber

@Composable
fun ScheduleSearchRouteScreenRoot(
    viewModel: ScheduleCreateViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.effect) { effect ->
        when(effect) {
            ScheduleCreateEffect.OnBack -> onBack()
        }
    }

    ScheduleSearchRouteScreen(
        state = state,
        onEvent = viewModel::onEvent,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScheduleSearchRouteScreen(
    modifier: Modifier = Modifier,
    state: ScheduleCreateState,
    onEvent: (ScheduleCreateEvent) -> Unit
) {
    Scaffold(
        modifier = modifier,
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(color = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .background(color = Color.White)
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    imageVector = ArrowLeft,
                    contentDescription = null
                )

                BasicTextField(
                    modifier = Modifier.weight(1f),
                    value = state.search,
                    onValueChange = {
                        Timber.e("onValueChanger $it")
                        onEvent(ScheduleCreateEvent.OnChangeSearch(it))
                                    },
                    textStyle = MaterialTheme.typography.bodyMid16.copy(
                        color = gray17
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onEvent(ScheduleCreateEvent.OnSearch(BottomSheetState.SEARCH))
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    decorationBox = { innerTextField ->
                        Box(
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (state.search.isEmpty()) {
                                Text(
                                    text = "장소를 검색해보세요!",
                                    style = MaterialTheme.typography.bodyMid16.copy(
                                        color = gray60
                                    )
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                if (state.search.isNotEmpty()) {
                    Icon(
                        imageVector = Delete,
                        contentDescription = null
                    )
                }

                Icon(
                    imageVector = SearchOutline,
                    contentDescription = null
                )
            }
            HorizontalDivider(color = gray95)

            if (state.search.isEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 20.dp, vertical = 40.dp),
                ) {
                    item {
                        Text(
                            text = "최근 검색어",
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = gray17
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    items(3) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Location,
                                contentDescription = null,
                                tint = gray95
                            )

                            Text(
                                modifier = Modifier.weight(1f),
                                text = "장소 이름 ${it + 1}",
                                style = MaterialTheme.typography.bodyMid16.copy(
                                    color = gray25
                                )
                            )

                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Default.Close,
                                contentDescription = null,
                                tint = gray60
                            )
                        }
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White)
                        .weight(1f)
                ) {
                    stickyHeader {
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
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onEvent(ScheduleCreateEvent.OnSearch(BottomSheetState.DETAIL)) }
                                .padding(horizontal = 20.dp, vertical = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                imageVector = Location,
                                contentDescription = null,
                                tint = gray95
                            )

                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "장소명${it + 1}",
                                        style = MaterialTheme.typography.bodySemiBold16.copy(
                                            color = gray17
                                        )
                                    )
                                    Text(
                                        text = "카테고리",
                                        style = MaterialTheme.typography.bodySemiBold12.copy(
                                            color = gray60
                                        )
                                    )
                                }

                                Text(
                                    text = "00시 00동",
                                    style = MaterialTheme.typography.captionRegular12.copy(
                                        color = gray60
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ScheduleSearchRouteScreenPreview(modifier: Modifier = Modifier) {
    RoadreamTheme {
        ScheduleSearchRouteScreen(
            state = ScheduleCreateState(),
            onEvent = {}
        )
    }
}