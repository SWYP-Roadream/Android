package com.yeogijeogi.presentation.schedule.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import com.yeogijeogi.presentation.theme.ui.theme.Star
import com.yeogijeogi.presentation.theme.ui.theme.bodyMid18
import com.yeogijeogi.presentation.theme.ui.theme.captionRegular12
import com.yeogijeogi.presentation.theme.ui.theme.gray17
import com.yeogijeogi.presentation.theme.ui.theme.gray40
import com.yeogijeogi.presentation.theme.ui.theme.gray95

@Composable
fun PlaceItem(
    modifier: Modifier = Modifier,
    placeName: String,
    categoryName: String,
    starRatio: String,
    starCount: Int,
    address: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = placeName,
                style = MaterialTheme.typography.bodyMid18.copy(
                    color = gray17
                )
            )

            Text(
                text = categoryName,
                style = MaterialTheme.typography.captionRegular12.copy(
                    color = gray40
                )
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Star,
                contentDescription = null,
                tint = gray40
            )
            Text(
                text = "$starRatio ($starCount)",
                style = MaterialTheme.typography.captionRegular12.copy(
                    color = gray40
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "•",
                style = MaterialTheme.typography.captionRegular12.copy(
                    color = gray95
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = address,
                style = MaterialTheme.typography.captionRegular12.copy(
                    color = gray40
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(10) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = Color.LightGray)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PlaceItemPreview() {
    RoadreamTheme {
        PlaceItem(
            placeName = "장소 이름",
            categoryName = "카테고리",
            starRatio = "4.6",
            starCount = 0,
            address = "OO시 OO동",
            onClick = {}
        )
    }
}