package com.yeogijeogi.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.bodySemiBold16
import com.yeogijeogi.presentation.theme.ui.theme.captionRegular12
import com.yeogijeogi.presentation.theme.ui.theme.gray10
import com.yeogijeogi.presentation.theme.ui.theme.gray60

@Composable
fun PathInfoItem(
    modifier: Modifier = Modifier,
    title: String,
    location: String,
    date: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White),
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color.Gray)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.Top)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySemiBold16.copy(
                    color = gray10
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = location,
                style = MaterialTheme.typography.captionRegular12.copy(
                    color = gray60
                )
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = date,
                style = MaterialTheme.typography.captionRegular12.copy(
                    color = gray60
                )
            )
        }

        DDayBadge(
            modifier = Modifier.align(Alignment.CenterVertically),
            day = "D-2"
        )
    }
}

@Preview
@Composable
private fun PathInfoItemPreview() {
    RoadreamTheme {
        PathInfoItem(
            title = "여름 휴가 - 일본 일정",
            location = "오사카, 후쿠오카",
            date = "7월 22일 ~ 8월 1일"
        )
    }
}