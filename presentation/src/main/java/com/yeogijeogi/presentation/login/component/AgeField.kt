package com.yeogijeogi.presentation.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.yeogijeogi.presentation.component.RoundButton
import com.yeogijeogi.domain.enums.Age

@Composable
fun AgeField(
    modifier: Modifier = Modifier,
    selected: Age?,
    onClick: (Age?) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        columns = GridCells.Fixed(3)
    ) {
        items(
            Age.entries
        ) { item ->
            RoundButton(
                modifier = Modifier.fillMaxWidth(),
                text = item.ageName,
                backGroundColor = if (selected == item) MaterialTheme.colorScheme.primary else Color.White,
                containerColor = if (selected == item) Color.White else Color.Black,
                isCheck = selected == item,
                onClick = { onClick(item) }
            )
        }
    }
}