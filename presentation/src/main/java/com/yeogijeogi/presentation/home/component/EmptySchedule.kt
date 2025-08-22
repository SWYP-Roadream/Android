package com.yeogijeogi.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yeogijeogi.presentation.theme.ui.theme.PlusIcon
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.black
import com.yeogijeogi.presentation.theme.ui.theme.bodySemiBold12
import com.yeogijeogi.presentation.theme.ui.theme.bodySemiBold14
import com.yeogijeogi.presentation.theme.ui.theme.gray100
import com.yeogijeogi.presentation.theme.ui.theme.gray80

@Composable
fun EmptySchedule(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = gray80, shape = RoundedCornerShape(12.dp))
            .padding(vertical = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "나만의 일정을 만들어 보세요!",
            style = MaterialTheme.typography.bodySemiBold14.copy(
                color = black
            )
        )

        Row(
            modifier = Modifier
                .height(32.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Icon(
                imageVector = PlusIcon,
                contentDescription = null,
                tint = gray100
            )

            Text(
                text = "일정 만들기",
                style = MaterialTheme.typography.bodySemiBold12.copy(
                    color = gray100
                )
            )
        }
    }
}

@Preview
@Composable
private fun EmptySchedulePreview() {
    RoadreamTheme {
        EmptySchedule()
    }
}