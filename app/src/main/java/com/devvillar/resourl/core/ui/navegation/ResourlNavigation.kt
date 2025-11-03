package com.devvillar.resourl.core.ui.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devvillar.resourl.features.auth.presentation.screens.AccountVerificationScreen
import com.devvillar.resourl.features.auth.presentation.screens.ForgotPasswordScreen
import com.devvillar.resourl.features.auth.presentation.screens.LoginScreen
import com.devvillar.resourl.features.auth.presentation.screens.RegisterScreen

@Composable
fun ResourlNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                onNavigateToForgotPassword = {
                    navController.navigate(Screen.ForgotPassword.route)
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(
                onNavigateToAccountVerification = {
                    navController.navigate(Screen.AccountVerification.route)
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.AccountVerification.route) {
            AccountVerificationScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                onNavigateToResetPassword = {
                    navController.popBackStack()
                }

            )
        }

    }
}

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object ForgotPassword : Screen("forgot_password")
    object AccountVerification : Screen("account_verification")
    object Home : Screen("home")
}