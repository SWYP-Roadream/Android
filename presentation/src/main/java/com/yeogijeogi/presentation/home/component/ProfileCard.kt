package com.yeogijeogi.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yeogijeogi.domain.model.enums.Mbti
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.gray0
import com.yeogijeogi.presentation.theme.ui.theme.bodyMid16
import com.yeogijeogi.presentation.theme.ui.theme.captionRegular12
import com.yeogijeogi.presentation.theme.ui.theme.gray80

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    nickname: String,
    mbti: Mbti,
    info: String,
) {
    Row(
        modifier = modifier
            .background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(color = Color.Gray)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = nickname,
                style = MaterialTheme.typography.bodyMid16.copy(color = gray0)
            )

            Text(
                text = info,
                style = MaterialTheme.typography.captionRegular12.copy(
                    color = gray80
                )
            )

            Text(
                text = mbti.name,
                style = MaterialTheme.typography.captionRegular12.copy(
                    color = gray80
                )
            )
        }
    }
}

@Preview
@Composable
private fun ProfileCardPreview() {
    RoadreamTheme {
        ProfileCard(
            nickname = "드림",
            info = "20대 · 남",
            mbti = Mbti.ESTJ
        )
    }
}