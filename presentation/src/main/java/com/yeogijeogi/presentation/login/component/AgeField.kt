package com.yeogijeogi.presentation.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yeogijeogi.domain.model.enums.Age
import com.yeogijeogi.presentation.component.OutlineButton
import com.yeogijeogi.presentation.component.RoundButton

@Composable
fun AgeField(
    modifier: Modifier = Modifier,
    selected: Age?,
    onClickAge: (Age?) -> Unit,
    onClick: () -> Unit
) {
    Column {
        LazyVerticalGrid(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            columns = GridCells.Fixed(3)
        ) {
            items(
                Age.entries
            ) { item ->
                OutlineButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = item.ageName,
                    isSelected = selected == item,
                    onClick = { onClickAge(item) }
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