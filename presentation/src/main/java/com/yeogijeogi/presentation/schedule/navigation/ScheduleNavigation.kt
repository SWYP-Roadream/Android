package com.yeogijeogi.presentation.schedule.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.yeogijeogi.presentation.schedule.screen.ScheduleCreatedRouteScreenRoot
import com.yeogijeogi.presentation.schedule.screen.ScheduleDateScreenRoot
import com.yeogijeogi.presentation.schedule.screen.ScheduleRouteScreenRoot
import com.yeogijeogi.presentation.schedule.screen.ScheduleSearchRouteScreenRoot
import com.yeogijeogi.presentation.schedule.screen.ScheduleTitleScreenRoot
import com.yeogijeogi.presentation.schedule.viewmodel.ScheduleCreateViewModel

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
            ScheduleRouteScreenRoot(
                onClickCreated = {
                    navController.navigate(ScheduleCreate)
                }
            )
        }

        composable<ScheduleCreate> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry<ScheduleCreate>()
            }
            val viewModel = hiltViewModel<ScheduleCreateViewModel>(parentEntry)
            ScheduleCreatedRouteScreenRoot(
                viewModel = viewModel,
                onClickSearch = {
                    navController.navigate(ScheduleSearch)
                }
            )
        }

        composable<ScheduleSearch> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry<ScheduleCreate>()
            }
            val viewModel = hiltViewModel<ScheduleCreateViewModel>(parentEntry)
            ScheduleSearchRouteScreenRoot(
                viewModel = viewModel,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}