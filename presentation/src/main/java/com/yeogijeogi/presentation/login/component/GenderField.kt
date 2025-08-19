package com.yeogijeogi.presentation.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yeogijeogi.domain.enums.Gender
import com.yeogijeogi.presentation.component.RoundButton

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
            RoundButton(
                modifier = Modifier.weight(1f),
                text = gender.genderName,
                backGroundColor = if (selected == gender) MaterialTheme.colorScheme.primary else Color.White,
                containerColor = if (selected == gender) Color.White else Color.Black,
                isCheck = selected == gender,
                onClick = { onClick(gender) }
            )
        }
    }
}