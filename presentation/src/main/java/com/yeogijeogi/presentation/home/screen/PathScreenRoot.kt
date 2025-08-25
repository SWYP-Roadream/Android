package com.yeogijeogi.presentation.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yeogijeogi.presentation.component.PathInfoItem
import com.yeogijeogi.presentation.component.RoundButton
import com.yeogijeogi.presentation.theme.ui.theme.BookmarkOutline
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.gray17

@Composable
fun PathScreenRoot(
    onCreatedPath: () -> Unit,
) {
    PathScreen(
        onCreatedPath = onCreatedPath
    )
}

@Composable
fun PathScreen(
    modifier: Modifier = Modifier,
    onCreatedPath: () -> Unit,
) {
    val lazyState = rememberLazyListState()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "나의 일정",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = gray17
                )
            )

            Icon(
                imageVector = BookmarkOutline,
                contentDescription = null,
            )
        }

        LazyColumn(
            modifier = modifier
                .padding(top = 26.dp)
                .fillMaxWidth()
                .weight(1f)
                .background(color = Color.White),
            state = lazyState,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(5) {
                PathInfoItem(
                    title = "여름 휴가 - 일본 일정",
                    location = "오사카, 후쿠오카",
                    date = "7월 22일 ~ 8월 1일"
                )
            }
        }

        RoundButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            text = "나만의 여행 일정 만들기",
            onClick = onCreatedPath
        )
    }
}

@Preview
@Composable
private fun PathScreenPreview() {
    RoadreamTheme {
        PathScreen(
            onCreatedPath = {}
        )
    }
}