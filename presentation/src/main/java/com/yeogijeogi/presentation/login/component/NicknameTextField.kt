package com.yeogijeogi.presentation.login.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
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
import com.yeogijeogi.presentation.theme.ui.theme.captionRegular12
import com.yeogijeogi.presentation.theme.ui.theme.gray10
import com.yeogijeogi.presentation.theme.ui.theme.gray40
import com.yeogijeogi.presentation.theme.ui.theme.gray60
import com.yeogijeogi.presentation.theme.ui.theme.gray90

@Composable
fun NicknameTextField(
    modifier: Modifier = Modifier,
    value: String,
    onChangeValue: (String) -> Unit,
    onClick: () -> Unit,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BasicTextField(
                value = value,
                onValueChange = onChangeValue,
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMid16.copy(
                    color = gray10
                ),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 40.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(color = gray90)
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = "닉네임",
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
                modifier = Modifier.fillMaxWidth(),
                text = "${value.length}/10",
                style = MaterialTheme.typography.captionRegular12.copy(
                    color = gray40,
                    textAlign = TextAlign.End
                )
            )
        }

        if (value.isNotEmpty()) {
            RoundButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
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
private fun NicknameTextFieldPreview() {
    var value by remember {
        mutableStateOf("")
    }
    RoadreamTheme {
        NicknameTextField(
            modifier = Modifier.fillMaxSize(),
            value = value,
            onChangeValue = { value = it },
            onClick = {}
        )
    }
}