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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yeogijeogi.domain.model.enums.CompanionType
import com.yeogijeogi.presentation.component.OutlineButton
import com.yeogijeogi.presentation.component.RoundButton
import com.yeogijeogi.presentation.theme.ui.theme.bodyMid16
import com.yeogijeogi.presentation.theme.ui.theme.gray30

@Composable
fun CompanionField(
    modifier: Modifier = Modifier,
    selected: List<CompanionType>,
    onClickCompanion: (CompanionType) -> Unit,
    onClickSkip: () -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        LazyVerticalGrid(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(
                CompanionType.entries
            ) { item ->
                OutlineButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = item.companionName,
                    isSelected = selected.contains(item),
                    onClick = { onClickCompanion(item) }
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClickSkip),
            text = "SKIP",
            style = MaterialTheme.typography.bodyMid16.copy(
                color = gray30
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(28.dp))
        RoundButton(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            text = "Next",
            onClick = onClick
        )
    }

}