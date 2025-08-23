package com.yeogijeogi.presentation.theme.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.yeogijeogi.presentation.R

val HomeIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.home)

val PathIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.path)

val MyPageIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.my_page)

val PlusIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.plus)

val ArrowRightIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.arrow_right)

val BookmarkOutline: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.bookmark_outline)