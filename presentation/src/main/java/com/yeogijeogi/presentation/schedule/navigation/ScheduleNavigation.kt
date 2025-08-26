package com.yeogijeogi.presentation.schedule.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yeogijeogi.presentation.schedule.screen.ScheduleDateScreenRoot
import com.yeogijeogi.presentation.schedule.screen.ScheduleRouteScreenRoot
import com.yeogijeogi.presentation.schedule.screen.ScheduleTitleScreenRoot

fun NavGraphBuilder.scheduleNavigation(navController: NavHostController) {
    navigation<Schedule>(
        startDestination = ScheduleDate
    ) {
        composable<ScheduleDate> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry<Schedule>()
            }
            ScheduleDateScreenRoot(
                onNavigateTitle = {
                    navController.navigate(ScheduleTitle)
                }
            )
        }

        composable<ScheduleTitle> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry<Schedule>()
            }
            ScheduleTitleScreenRoot(
                onRouteClick = {
                    navController.navigate(ScheduleRoute)
                }
            )
        }

        composable<ScheduleRoute> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry<Schedule>()
            }
            ScheduleRouteScreenRoot()
        }
    }
}