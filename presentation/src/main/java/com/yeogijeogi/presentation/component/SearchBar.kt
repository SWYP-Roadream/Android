package com.yeogijeogi.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yeogijeogi.presentation.R
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.bodyMid16
import com.yeogijeogi.presentation.theme.ui.theme.gray60

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onChangeValue: (String) -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shape = CircleShape,
        shadowElevation = 4.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            BasicTextField(
                modifier = Modifier.weight(1f),
                maxLines = 1,
                value = value,
                onValueChange = onChangeValue,
                textStyle = MaterialTheme.typography.bodyMid16,
                decorationBox = { innerTextField ->
                    Box {
                        if (value.isEmpty()) {
                            Text(
                                text = "여행지, 장소 검색",
                                style = MaterialTheme.typography.bodyMid16.copy(
                                    color = gray60
                                )
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Image(
                painter = painterResource(R.drawable.search_outline),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    RoadreamTheme {
        SearchBar(
            value = "",
            onChangeValue = {}
        )
    }
}