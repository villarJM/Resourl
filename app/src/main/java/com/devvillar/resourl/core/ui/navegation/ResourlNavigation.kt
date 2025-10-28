package com.devvillar.resourl.core.ui.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devvillar.resourl.features.auth.presentation.screens.LoginScreen

@Composable
fun ResourlNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen()
        }

    }
}

sealed class Screen(val route: String) {
    object Login : Screen("login")
}