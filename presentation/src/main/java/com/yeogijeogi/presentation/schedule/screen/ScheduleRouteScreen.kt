package com.yeogijeogi.presentation.schedule.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.rememberCameraPositionState
import com.yeogijeogi.presentation.schedule.component.RouteItem
import com.yeogijeogi.presentation.theme.ui.theme.ArrowLeft
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.bodySemiBold14
import com.yeogijeogi.presentation.theme.ui.theme.captionRegular12
import com.yeogijeogi.presentation.theme.ui.theme.gray100
import com.yeogijeogi.presentation.theme.ui.theme.gray17
import com.yeogijeogi.presentation.theme.ui.theme.gray40
import com.yeogijeogi.presentation.theme.ui.theme.gray60
import com.yeogijeogi.presentation.theme.ui.theme.gray97

@Composable
fun ScheduleRouteScreenRoot(
    onClickCreated: () -> Unit,
) {
    ScheduleRouteScreen(
        onClickCreated = onClickCreated
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleRouteScreen(
    modifier: Modifier = Modifier,
    onClickCreated: () -> Unit,
) {
    val cameraPositionState = rememberCameraPositionState()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 20.dp),
                navigationIcon = {
                    Icon(
                        imageVector = ArrowLeft,
                        contentDescription = null,
                    )
                },
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = gray100
                )
            )
        },
        containerColor = gray100
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "2박 3일 강원도 여행",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = gray17
                        )
                    )

                    TextButton(onClick = {}) {
                        Text(
                            text = "편집",
                            style = MaterialTheme.typography.bodySemiBold14.copy(
                                color = gray40
                            )
                        )
                    }
                }

                Text(
                    text = "2025. 8. 7 ~ 2025. 8. 10",
                    style = MaterialTheme.typography.captionRegular12.copy(
                        color = gray60
                    )
                )
            }

            GoogleMap(
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 40.dp)
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                cameraPositionState = cameraPositionState,
                uiSettings = MapUiSettings(
                    zoomControlsEnabled = false
                )
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                items(3) {
                    RouteItem(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        day = it + 1,
                        onClick = onClickCreated
                    )
                    if(it != 2) {
                        Spacer(modifier = Modifier.height(24.dp))
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            color = gray97
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ScheduleCreateScreenPreview() {
    RoadreamTheme {
        ScheduleRouteScreen(
            onClickCreated = {}
        )
    }
}