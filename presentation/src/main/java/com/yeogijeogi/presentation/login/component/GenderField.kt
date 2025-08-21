package com.yeogijeogi.presentation.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yeogijeogi.domain.model.enums.Gender
import com.yeogijeogi.presentation.component.OutlineButton
import com.yeogijeogi.presentation.component.RoundButton
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme

@Composable
fun GenderField(
    modifier: Modifier = Modifier,
    selected: Gender?,
    onClickGender: (Gender?) -> Unit,
    onClick: () -> Unit
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Gender.entries.forEach { gender ->
                OutlineButton(
                    modifier = Modifier.weight(1f),
                    text = gender.genderName,
                    isSelected = selected == gender,
                    onClick = { onClickGender(gender) }
                )
            }
        }

        RoundButton(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            text = "Next",
            enabled = selected != null,
            onClick = onClick
        )
    }
}

@Preview
@Composable
private fun GenderFieldPreview() {
    RoadreamTheme {
        GenderField(
            selected = Gender.MALE,
            onClickGender = {},
            onClick = {}
        )
    }
}