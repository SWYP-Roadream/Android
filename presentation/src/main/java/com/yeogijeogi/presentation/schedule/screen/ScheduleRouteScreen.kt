package com.yeogijeogi.presentation.schedule.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yeogijeogi.presentation.component.RoundButton
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.bodyMid16
import com.yeogijeogi.presentation.theme.ui.theme.bodyRegular14
import com.yeogijeogi.presentation.theme.ui.theme.captionRegular12
import com.yeogijeogi.presentation.theme.ui.theme.gray0
import com.yeogijeogi.presentation.theme.ui.theme.gray17
import com.yeogijeogi.presentation.theme.ui.theme.gray30
import com.yeogijeogi.presentation.theme.ui.theme.gray40
import com.yeogijeogi.presentation.theme.ui.theme.gray60
import com.yeogijeogi.presentation.theme.ui.theme.gray97
import com.yeogijeogi.presentation.theme.ui.theme.gray99

@Composable
fun ScheduleRouteScreenRoot() {
    ScheduleRouteScreen()
}

@Composable
fun ScheduleRouteScreen(modifier: Modifier = Modifier) {
    var titleValue by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            Box {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = null
                    )
                }
                HorizontalDivider(modifier = Modifier.align(Alignment.BottomCenter), color = gray97)
            }
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 24.dp)
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "여행 제목과 간단한 설명을 \n남겨주세요",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = gray17
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column {
                Text(
                    text = "제목",
                    style = MaterialTheme.typography.bodyRegular14.copy(
                        color = gray30
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                BasicTextField(
                    value = titleValue,
                    onValueChange = {},
                    maxLines = 1,
                    textStyle = MaterialTheme.typography.bodyMid16.copy(
                        color = gray17
                    ),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 48.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(color = gray99)
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (titleValue.isEmpty()) {
                                Text(
                                    text = "여행 제목을 적어주세요",
                                    style = MaterialTheme.typography.bodyMid16.copy(
                                        color = gray60
                                    )
                                )
                            }
                            innerTextField()
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "${titleValue.length}/10",
                    style = MaterialTheme.typography.captionRegular12.copy(
                        color = gray40,
                        textAlign = TextAlign.End
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                Text(
                    text = "설명",
                    style = MaterialTheme.typography.bodyRegular14.copy(
                        color = gray30
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                BasicTextField(
                    value = description,
                    onValueChange = {},
                    textStyle = MaterialTheme.typography.bodyMid16.copy(
                        color = gray17
                    ),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 90.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(color = gray99)
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            contentAlignment = Alignment.TopStart
                        ) {
                            if (description.isEmpty()) {
                                Text(
                                    text = "여행에 대해 간단히 소개해주세요",
                                    style = MaterialTheme.typography.bodyMid16.copy(
                                        color = gray60
                                    )
                                )
                            }
                            innerTextField()
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "${titleValue.length}/50",
                    style = MaterialTheme.typography.captionRegular12.copy(
                        color = gray40,
                        textAlign = TextAlign.End
                    )
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            RoundButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                text = "다음",
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun ScheduleCreateScreenPreview() {
    RoadreamTheme {
        ScheduleRouteScreen()
    }
}