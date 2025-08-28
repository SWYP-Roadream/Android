package com.yeogijeogi.presentation.schedule.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
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
import com.yeogijeogi.presentation.theme.ui.theme.BookmarkOutline
import com.yeogijeogi.presentation.theme.ui.theme.RoadreamTheme
import com.yeogijeogi.presentation.theme.ui.theme.Star
import com.yeogijeogi.presentation.theme.ui.theme.bodyMid14
import com.yeogijeogi.presentation.theme.ui.theme.bodyMid16
import com.yeogijeogi.presentation.theme.ui.theme.bodyRegular14
import com.yeogijeogi.presentation.theme.ui.theme.bodySemiBold14
import com.yeogijeogi.presentation.theme.ui.theme.captionRegular12
import com.yeogijeogi.presentation.theme.ui.theme.gray100
import com.yeogijeogi.presentation.theme.ui.theme.gray17
import com.yeogijeogi.presentation.theme.ui.theme.gray40
import com.yeogijeogi.presentation.theme.ui.theme.gray95

@Composable
fun PlaceDetailItem(
    modifier: Modifier = Modifier,
    placeName: String,
    categoryName: String,
    starRatio: String,
    reviewCount: Int,
    address: String
) {
    Column(
        modifier = modifier
            .background(color = Color.White)
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = placeName,
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = gray17
                        )
                    )

                    Text(
                        text = categoryName,
                        style = MaterialTheme.typography.bodyMid16.copy(
                            color = gray40
                        )
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Star,
                        contentDescription = null,
                        tint = gray40
                    )
                    Text(
                        text = starRatio,
                        style = MaterialTheme.typography.bodyMid14.copy(
                            color = gray40
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.bodyRegular14.copy(
                            color = gray95
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "후기 ($reviewCount)",
                        style = MaterialTheme.typography.bodyMid14.copy(
                            color = gray40
                        )
                    )
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = gray40
                    )
                }

                Text(
                    text = address,
                    style = MaterialTheme.typography.bodyRegular14.copy(
                        color = gray40
                    )
                )
            }

            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = BookmarkOutline,
                contentDescription = null,
                tint = gray95
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(10) {
                Box(
                    modifier = Modifier
                        .size(160.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = Color.LightGray)
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        HorizontalDivider(color = gray95)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 8.dp, bottom = 16.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = gray100
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "일정추가",
                style = MaterialTheme.typography.bodySemiBold14.copy(
                    color = gray100
                )
            )
        }

    }
}

@Preview
@Composable
private fun PlaceDetailItemPreview() {
    RoadreamTheme {
        PlaceDetailItem(
            placeName = "장소 이름",
            categoryName = "카테고리",
            starRatio = "4.6",
            reviewCount = 3,
            address = "도시이름 OO구"
        )
    }
}