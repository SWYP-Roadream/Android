package com.yeogijeogi.presentation.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.yeogijeogi.presentation.home.screen.HomeRoute
import com.yeogijeogi.presentation.schedule.navigation.Schedule

fun NavGraphBuilder.homeNavigation(navController: NavHostController) {
    composable<HomeRoute> {
        HomeRoute(
            onCreatedPath = {
                navController.navigate(Schedule)
            }
        )
    }
}