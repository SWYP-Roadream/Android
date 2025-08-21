package com.yeogijeogi.presentation.login.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.bodySemiBold14
import com.yeogijeogi.presentation.theme.ui.theme.gray10
import com.yeogijeogi.presentation.theme.ui.theme.gray30

@Composable
fun OnBoardingBase(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(color = Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(48.dp),
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = gray10
                ),
                textAlign = TextAlign.Center
            )
            subTitle?.let {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySemiBold14.copy(
                        color = gray30
                    ),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
        content()
    }
}

@Preview
@Composable
private fun OnBoardingBasePreview() {
    RoadreamTheme {
        OnBoardingBase(
            title = "'여기 저기'에 온 걸 환영해\n닉네임을 설정해줘",
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(color = Color.Red)
                ) {

                }
            },
        )
    }
}