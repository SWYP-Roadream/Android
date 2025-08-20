package com.yeogijeogi.presentation.login.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme

@Composable
fun MbtiField(
    modifier: Modifier = Modifier,
    value: String,
    onChangeValue: (String) -> Unit,
    onClickSkip: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Text(
            modifier = Modifier.align(Alignment.TopCenter),
            text = "(MBTI 유형별로 AI 가 추천 장소를 알려주려고 해!)",
            textAlign = TextAlign.Center,
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BasicTextField(
                value = value,
                onValueChange = onChangeValue,
                singleLine = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Center
                ),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "생각이 안나면 SKIP 후 마이페이지에서 수정 가능해!",
                textAlign = TextAlign.Center,
                color = Color.Gray
            )

        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onClickSkip),
                text = "SKIP",
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview
@Composable
private fun MbtiFieldPreview() {
    RoadreamTheme {
        MbtiField(
            modifier = Modifier.fillMaxSize(),
            value = "",
            onClickSkip = {},
            onChangeValue = {}
        )
    }
}