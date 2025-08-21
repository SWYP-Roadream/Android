package com.yeogijeogi.presentation.theme.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    displayLarge = TextStyle(
        fontSize = 40.sp,
        fontWeight = FontWeight.W700,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W700,
        fontSize = 24.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp,
    ),
)

val Typography.bodyMid18: TextStyle
    get() = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.W500
    )

val Typography.bodySemiBold16: TextStyle
    get() = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
    )

val Typography.bodyMid16: TextStyle
    get() = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W500,
    )

val Typography.bodySemiBold14: TextStyle
    get() = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
    )

val Typography.bodyMid14: TextStyle
    get() = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W500,
    )

val Typography.bodyRegular14: TextStyle
    get() = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
    )

val Typography.bodySemiBold12: TextStyle
    get() = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
    )

val Typography.captionRegular12: TextStyle
    get() = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
    )