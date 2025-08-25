package com.yeogijeogi.presentation.home.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yeogijeogi.presentation.home.model.BottomNavigationItem
import com.yeogijeogi.presentation.home.navigation.HomeRouteMain
import com.yeogijeogi.presentation.home.navigation.HomeRouteMyPage
import com.yeogijeogi.presentation.home.navigation.HomeRoutePath
import com.yeogijeogi.presentation.theme.ui.theme.HomeIcon
import com.yeogijeogi.presentation.theme.ui.theme.MyPageIcon
import com.yeogijeogi.presentation.theme.ui.theme.PathIcon
import com.yeogijeogi.presentation.theme.ui.theme.captionRegular12
import com.yeogijeogi.presentation.theme.ui.theme.selectNavigationColor
import com.yeogijeogi.presentation.theme.ui.theme.unSelectNavigationColor
import timber.log.Timber

@Composable
fun HomeRoute(
    onCreatedPath: () -> Unit,
) {
    val navController = rememberNavController()
    val barItems = listOf(
        BottomNavigationItem(
            title = "홈",
            icon = HomeIcon
        ),
        BottomNavigationItem(
            title = "일정",
            icon = PathIcon
        ),
        BottomNavigationItem(
            title = "마이페이지",
            icon = MyPageIcon
        ),
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                contentColor = Color.White
            ) {
                barItems.forEachIndexed { index, item ->
                    val color = if(selectedItemIndex == index) selectNavigationColor else unSelectNavigationColor
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                tint = color
                            )
                        },
                        label = {
                            Text(
                                item.title,
                                style = MaterialTheme.typography.captionRegular12.copy(
                                    color = color
                                )
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.White,
                        ),
                        onClick = {
                            Timber.e("selectedIndex $selectedItemIndex index $index")
                            if(selectedItemIndex != index) {
                                when(index) {
                                    0 -> navController.navigate(HomeRouteMain) {
                                        popUpTo<HomeRouteMain>()
                                    }
                                    1 -> navController.navigate(HomeRoutePath) {
                                        popUpTo<HomeRouteMain>()
                                    }
                                    2 -> navController.navigate(HomeRouteMyPage) {
                                        popUpTo<HomeRouteMain>()
                                    }
                                }
                                selectedItemIndex = index
                            }
                        },
                    )
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            startDestination = HomeRouteMain,
            navController = navController
        ) {
            composable<HomeRouteMain> {
                MainScreenRoot()
            }

            composable<HomeRoutePath> {
                PathScreenRoot(
                    onCreatedPath = onCreatedPath
                )
            }

            composable<HomeRouteMyPage> {
                MyPageScreenRoot()
            }
        }
    }
}