package com.yeogijeogi.roadream

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.yeogijeogi.presentation.home.navigation.HomeRoute
import com.yeogijeogi.presentation.home.navigation.homeNavigation
import com.yeogijeogi.presentation.login.navigation.Login
import com.yeogijeogi.presentation.login.navigation.LoginRoute
import com.yeogijeogi.presentation.login.navigation.loginNavigation

@Composable
fun MainRoute(
    modifier: Modifier = Modifier,
    isLoggedIn: Boolean
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = if (isLoggedIn) HomeRoute else Login
    ) {
        navigation<Login>(startDestination = LoginRoute) {
            loginNavigation(
                navController,
                goMain = {
                    navController.navigate(HomeRoute) {
                        popUpTo(Login) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        homeNavigation(navController)
    }
}