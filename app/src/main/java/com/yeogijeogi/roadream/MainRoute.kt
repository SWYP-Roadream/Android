package com.yeogijeogi.roadream

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.yeogijeogi.presentation.home.navigation.homeNavigation
import com.yeogijeogi.presentation.login.navigation.Login
import com.yeogijeogi.presentation.login.navigation.LoginRoute
import com.yeogijeogi.presentation.login.navigation.loginNavigation

@Composable
fun MainRoute(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Login
    ) {
        navigation<Login>(startDestination = LoginRoute) {
            loginNavigation(navController)
        }
        homeNavigation(navController)
    }
}