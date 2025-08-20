package com.yeogijeogi.presentation.login.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yeogijeogi.domain.model.enums.CompanionType
import com.yeogijeogi.presentation.component.OutlineButton
import com.yeogijeogi.presentation.component.RoundButton

@Composable
fun CompanionField(
    modifier: Modifier = Modifier,
    selected: List<CompanionType>,
    onClick: (CompanionType) -> Unit,
    onClickSkip: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ){
        LazyVerticalGrid(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            columns = GridCells.Fixed(2)
        ) {
            item(
                span = { GridItemSpan(2) }
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "(세가지 골라줘)",
                    textAlign = TextAlign.Center
                )
            }

            items(
                CompanionType.entries
            ) { item ->
                OutlineButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = item.companionName,
                    isSelected = selected.contains(item),
                    onClick = { onClick(item) }
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
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