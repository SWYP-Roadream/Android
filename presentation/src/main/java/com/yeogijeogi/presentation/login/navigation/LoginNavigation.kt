package com.yeogijeogi.presentation.login.navigation

import android.util.Log
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.yeogijeogi.presentation.login.LoginRoot
import com.yeogijeogi.presentation.login.LoginViewModel
import com.yeogijeogi.presentation.login.OnBoardingRoot

fun NavGraphBuilder.loginNavigation(navController: NavHostController, goMain: () -> Unit) {
    composable<LoginRoute> { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry<Login>()
        }
        val vm: LoginViewModel = viewModel(viewModelStoreOwner = parentEntry)
        LoginRoot(
            viewModel = vm,
            onBoarding = {
                navController.navigate(OnBoardingRoute)
            }
        )
    }

    composable<OnBoardingRoute> { backStackEntry ->
        val parentEntry = remember(backStackEntry) {
            navController.getBackStackEntry<Login>()
        }
        val vm: LoginViewModel = viewModel(viewModelStoreOwner = parentEntry)
        OnBoardingRoot(
            viewModel = vm,
            onBack = { navController.popBackStack() },
            goMain = goMain
        )
    }
}