package com.devvillar.resourl.features.auth.presentation.routes

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.devvillar.resourl.features.auth.presentation.effects.LoginEffect
import com.devvillar.resourl.features.auth.presentation.screens.LoginScreen
import com.devvillar.resourl.features.auth.presentation.viewmodels.LoginViewModel

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onNavigateToHome: () -> Unit
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is LoginEffect.NavigateToRegister -> onNavigateToRegister()
                is LoginEffect.NavigateToForgotPassword -> onNavigateToForgotPassword()
                is LoginEffect.NavigateToHome -> onNavigateToHome()
                is LoginEffect.ShowError -> snackBarHostState.showSnackbar(effect.message)
            }
        }
    }

    LoginScreen(
        state = state,
        snackBarHostState = snackBarHostState,
        onEvent = viewModel::onEvent
    )
}