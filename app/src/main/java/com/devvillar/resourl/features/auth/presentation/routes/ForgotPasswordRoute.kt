package com.devvillar.resourl.features.auth.presentation.routes

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.devvillar.resourl.features.auth.presentation.effects.ForgotPasswordEffect
import com.devvillar.resourl.features.auth.presentation.screens.ForgotPasswordScreen
import com.devvillar.resourl.features.auth.presentation.viewmodels.ForgotPasswordViewModel

@Composable
fun ForgotPasswordRoute(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    onNavigateToAccountVerification: () -> Unit,
    onNavigateToLogin: () -> Unit
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is ForgotPasswordEffect.NavigateToAccountVerification -> onNavigateToAccountVerification()
                is ForgotPasswordEffect.NavigateToLogin -> onNavigateToLogin()
                is ForgotPasswordEffect.ShowError -> snackBarHostState.showSnackbar(effect.message)
            }
        }
    }

    ForgotPasswordScreen(
        state = state,
        snackBarHostState = snackBarHostState,
        onEvent = viewModel::onEvent
    )


}