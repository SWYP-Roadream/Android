package com.yeogijeogi.presentation.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yeogijeogi.presentation.component.SearchBar
import com.yeogijeogi.presentation.home.component.AISchedule
import com.yeogijeogi.presentation.home.component.EmptySchedule
import com.yeogijeogi.presentation.home.component.PopularSchedule
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.gray10

@Composable
fun MainScreenRoot() {
    MainScreen()
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val lazyState = rememberLazyListState()
    val pagerState = rememberPagerState { 5 }
    LazyColumn(
        state = lazyState,
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 30.dp)
    ) {
        item {
            SearchBar(
                modifier = modifier,
                value = "",
                onChangeValue = {}
            )
            Spacer(modifier = Modifier.height(26.dp))
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "로드림 님!\n여행을 더나봐요!",
                    style = MaterialTheme.typography.titleLarge.copy(color = gray10)
                )

                Text(
                    text = "D-Day",
                    style = MaterialTheme.typography.displayLarge.copy(color = gray10)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
        item {
            EmptySchedule()
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            PopularSchedule(pagerState = pagerState)
            Spacer(modifier = Modifier.height(30.dp))
        }
        item { AISchedule() }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    RoadreamTheme {
        MainScreen()
    }
}