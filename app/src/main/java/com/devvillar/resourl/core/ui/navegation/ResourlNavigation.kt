package com.devvillar.resourl.core.ui.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devvillar.resourl.features.addresource.presentation.routes.AddResourceRoute
import com.devvillar.resourl.features.auth.presentation.routes.ForgotPasswordRoute
import com.devvillar.resourl.features.auth.presentation.routes.LoginRoute
import com.devvillar.resourl.features.auth.presentation.screens.AccountVerificationScreen
import com.devvillar.resourl.features.auth.presentation.screens.ForgotPasswordScreen
import com.devvillar.resourl.features.auth.presentation.screens.LoginScreen
import com.devvillar.resourl.features.auth.presentation.screens.RegisterScreen

@Composable
fun ResourlNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenNames.Login.route
    ) {
        composable(ScreenNames.Login.route) {
            LoginRoute(
                onNavigateToRegister = {
                    navController.navigate(ScreenNames.Register.route)
                },
                onNavigateToForgotPassword = {
                    navController.navigate(ScreenNames.ForgotPassword.route)
                },
                onNavigateToHome = {
                    navController.navigate(ScreenNames.AddResource.route)
                }
            )
        }

        composable(ScreenNames.Register.route) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable(ScreenNames.ForgotPassword.route) {
            ForgotPasswordRoute(
                onNavigateToAccountVerification = {
                    navController.navigate(ScreenNames.AccountVerification.route)
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable(ScreenNames.AccountVerification.route) {
            AccountVerificationScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                onNavigateToResetPassword = {
                    navController.popBackStack()
                }

            )
        }

        composable(ScreenNames.AddResource.route) {
            AddResourceRoute(
                onNavigateToSearchResource = { },
                onNavigateToEditResource = { }
            )
        }

    }
}

sealed class ScreenNames(val route: String) {
    object Login : ScreenNames("login")
    object Register : ScreenNames("register")
    object ForgotPassword : ScreenNames("forgot_password")
    object AccountVerification : ScreenNames("account_verification")
    object AddResource : ScreenNames("add_resource")
}