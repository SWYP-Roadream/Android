package com.yeogijeogi.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.bodyMid14
import com.yeogijeogi.presentation.theme.ui.theme.gray60
import com.yeogijeogi.presentation.theme.ui.theme.gray99

@Composable
fun FilterBadge(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    filterBadgeButtonColors: FilterBadgeButtonColors = rememberFilterBadgeButtonColors(),
    onClick: () -> Unit,
) {
    val backgroundColor = if (isSelected) {
        filterBadgeButtonColors.selectedBackgroundColor
    } else filterBadgeButtonColors.unSelectedBackgroundColor

    val textColor = if (isSelected) {
        filterBadgeButtonColors.selectedTextColor
    } else filterBadgeButtonColors.unSelectedTextColor

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(color = backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMid14.copy(
                color = textColor
            )
        )
    }
}

@Preview
@Composable
private fun FilterBadgePreview() {
    RoadreamTheme {
        FilterBadge(
            text = "음식점",
            isSelected = false,
            onClick = {}
        )
    }
}

data class FilterBadgeButtonColors(
    val selectedBackgroundColor: Color,
    val unSelectedBackgroundColor: Color,
    val selectedTextColor: Color,
    val unSelectedTextColor: Color
)

@Composable
fun rememberFilterBadgeButtonColors(
    selectedBackgroundColor: Color = MaterialTheme.colorScheme.secondary,
    unSelectedBackgroundColor: Color = gray99,
    selectedTextColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedTextColor: Color = gray60
): FilterBadgeButtonColors {
    return FilterBadgeButtonColors(
        selectedBackgroundColor = selectedBackgroundColor,
        unSelectedBackgroundColor = unSelectedBackgroundColor,
        selectedTextColor = selectedTextColor,
        unSelectedTextColor = unSelectedTextColor
    )
}