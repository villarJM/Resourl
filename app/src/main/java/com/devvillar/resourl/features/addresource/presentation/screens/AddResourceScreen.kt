package com.devvillar.resourl.features.addresource.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.devvillar.resourl.app.components.CustomTopBar
import com.devvillar.resourl.features.addresource.presentation.events.AddResourceEvent
import com.devvillar.resourl.features.addresource.presentation.states.AddResourceUIState

@Composable
fun AddResourceScreen(
    state: AddResourceUIState,
    snackBarHostState: SnackbarHostState,
    onEvent: (AddResourceEvent) -> Unit,
) {

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            CustomTopBar(
                title = "Add Resource",
                onEventLeft = { onEvent( AddResourceEvent.OnSearchResourceClick ) },
                iconLeft = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            LazyColumn() {

            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun AddResourceScreenPreview() {
    AddResourceScreen(
        state = AddResourceUIState(),
        snackBarHostState = SnackbarHostState(),
        onEvent = {}
    )
}