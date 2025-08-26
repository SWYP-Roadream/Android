package com.yeogijeogi.presentation.schedule.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yeogijeogi.presentation.component.OutlineButton
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.bodyMid16
import com.yeogijeogi.presentation.theme.ui.theme.bodySemiBold14
import com.yeogijeogi.presentation.theme.ui.theme.gray100
import com.yeogijeogi.presentation.theme.ui.theme.gray17
import com.yeogijeogi.presentation.theme.ui.theme.gray40
import com.yeogijeogi.presentation.theme.ui.theme.gray60
import com.yeogijeogi.presentation.theme.ui.theme.gray95

@Composable
fun RouteItem(
    modifier: Modifier = Modifier,
    day: Int,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = gray100)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${day}일차",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = gray17
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "2025.08.28(월)",
                style = MaterialTheme.typography.bodyMid16.copy(
                    color = gray60
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "편집",
                style = MaterialTheme.typography.bodySemiBold14.copy(
                    color = gray40
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onClick,
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(width = 1.dp, color = gray95)
        ) {
            Text(
                text = "장소 추가하기",
                style = MaterialTheme.typography.bodySemiBold14.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

@Preview
@Composable
private fun RouteItemPreview() {
    RoadreamTheme {
        RouteItem(
            day = 1,
            onClick = {}
        )
    }
}