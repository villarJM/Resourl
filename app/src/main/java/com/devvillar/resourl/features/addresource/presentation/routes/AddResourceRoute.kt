package com.devvillar.resourl.features.addresource.presentation.routes

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.devvillar.resourl.features.addresource.presentation.effects.AddResourceEffect
import com.devvillar.resourl.features.addresource.presentation.screens.AddResourceScreen
import com.devvillar.resourl.features.addresource.presentation.viewmodels.AddResourceViewModel

@Composable
fun AddResourceRoute(
    viewModel: AddResourceViewModel = hiltViewModel(),
    onNavigateToSearchResource: () -> Unit,
    onNavigateToEditResource: () -> Unit,
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is AddResourceEffect.NavigateToSearchResource -> onNavigateToSearchResource()
                is AddResourceEffect.NavigateToEditResource -> onNavigateToEditResource()
                is AddResourceEffect.ShowError -> snackBarHostState.showSnackbar(effect.message)
            }
        }
    }

    AddResourceScreen(
        state = state,
        snackBarHostState = snackBarHostState,
        onEvent = viewModel::onEvent
    )
}