package com.yeogijeogi.presentation.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yeogijeogi.domain.model.enums.Mbti
import com.yeogijeogi.presentation.home.component.ProfileCard
import com.yeogijeogi.presentation.theme.ui.theme.AlarmOutline
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.SettingOutline
import com.yeogijeogi.presentation.theme.ui.theme.bodySemiBold16
import com.yeogijeogi.presentation.theme.ui.theme.gray17

@Composable
fun MyPageScreenRoot(modifier: Modifier = Modifier) {
    MyPageScreen()
}

@Composable
fun MyPageScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "마이페이지",
                style = MaterialTheme.typography.bodySemiBold16.copy(
                    color = gray17
                )
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = AlarmOutline,
                    contentDescription = null,
                )

                Icon(
                    imageVector = SettingOutline,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }

        ProfileCard(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            nickname = "드림",
            info = "20대 · 남",
            mbti = Mbti.ESTJ
        )
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    RoadreamTheme {
        MyPageScreen()
    }
}