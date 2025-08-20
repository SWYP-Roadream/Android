package com.yeogijeogi.presentation.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yeogijeogi.domain.model.enums.Gender
import com.yeogijeogi.presentation.component.OutlineButton

@Composable
fun GenderField(
    modifier: Modifier = Modifier,
    selected: Gender?,
    onClick: (Gender?) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Gender.entries.forEach { gender ->
            OutlineButton(
                modifier = Modifier.weight(1f),
                text = gender.genderName,
                isSelected = selected == gender,
                onClick = { onClick(gender) }
            )
        }
    }
}