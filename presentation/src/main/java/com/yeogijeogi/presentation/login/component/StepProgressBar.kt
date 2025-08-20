package com.yeogijeogi.presentation.login.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StepProgressBar(
    progress: Float,                 // 0f..1f
    modifier: Modifier = Modifier,
    height: Dp = 22.dp,
    trackColor: Color = Color.LightGray,
    indicatorColor: Color = MaterialTheme.colorScheme.primary,
    animationMillis: Int = 400
) {
    val animated by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = animationMillis, easing = FastOutSlowInEasing),
        label = "step-progress"
    )

    val shape = RoundedCornerShape(percent = 50)

    Box(
        modifier
            .fillMaxWidth()
            .height(height)
            .clip(shape)
            .background(trackColor)
    ) {
        Box(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(fraction = animated) // 진행률만큼 너비
                .clip(shape)                        // 둥근 끝처리
                .background(indicatorColor)
        )
    }
}