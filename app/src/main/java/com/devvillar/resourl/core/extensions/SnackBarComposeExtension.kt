package com.devvillar.resourl.core.extensions

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberSnackBarController(): SnackbarController {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    return remember {
        SnackbarController(snackBarHostState, scope)
    }
}

class SnackbarController(
    private val snackbarHostState: SnackbarHostState,
    private val scope: CoroutineScope
) {
    fun showSuccess(message: String) {
        scope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }

    fun showError(message: String) {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Long
            )
        }
    }
}