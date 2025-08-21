package com.yeogijeogi.presentation.login.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yeogijeogi.domain.model.enums.Mbti
import com.yeogijeogi.presentation.component.OutlineButton
import com.yeogijeogi.presentation.component.RoundButton
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.bodyMid16
import com.yeogijeogi.presentation.theme.ui.theme.bodyRegular14
import com.yeogijeogi.presentation.theme.ui.theme.gray30
import com.yeogijeogi.presentation.theme.ui.theme.textPrimary

@Composable
fun MbtiField(
    modifier: Modifier = Modifier,
    selected: Mbti?,
    onClickMbti: (Mbti) -> Unit,
    onClickSkip: () -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        LazyVerticalGrid(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            columns = GridCells.Fixed(4)
        ) {
            items(
                Mbti.entries
            ) { item ->
                OutlineButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = item.name,
                    isSelected = selected == item,
                    onClick = { onClickMbti(item) }
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Column {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "생각이 안나면 SKIP 후 마이페이지에서 수정 가능해!",
                style = MaterialTheme.typography.bodyRegular14.copy(
                    color = textPrimary,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onClickSkip),
                text = "SKIP",
                style = MaterialTheme.typography.bodyMid16.copy(
                    color = gray30,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(28.dp))
            RoundButton(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                text = "Next",
                onClick = onClick
            )
        }
    }
}

@Preview
@Composable
private fun MbtiFieldPreview() {
    RoadreamTheme {
        MbtiField(
            modifier = Modifier.fillMaxSize(),
            selected = Mbti.ENFP,
            onClickSkip = {},
            onClickMbti = {},
            onClick = {}
        )
    }
}