package com.devvillar.resourl.features.auth.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devvillar.resourl.R
import com.devvillar.resourl.core.ui.components.CustomOutlinedTextField

@Composable
fun ForgotPasswordScreen(
    onNavigateToLogin: () -> Unit = {},
) {
    val snackBarHostState = remember { SnackbarHostState() }

//    val registerState by viewModel.registerUIState.collectAsStateWithLifecycle()
//    val validationState by viewModel.validationResult.collectAsStateWithLifecycle()
//
//    val email by viewModel.email.collectAsStateWithLifecycle()
//
//    val isFormValid by viewModel.isFormValid.collectAsStateWithLifecycle()


//    LaunchedEffect(registerState) {
//        when (registerState) {
//            is UIState.Success -> onNavigateToLogin()
//            is UIState.Error -> {
//                snackBarHostState.showSnackbar((registerState as UIState.Error).message)
//            }
//            else -> {}
//        }
//    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                FilledTonalIconButton(
                    modifier = Modifier
                        .size(50.dp),
                    onClick = { onNavigateToLogin() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    stringResource(R.string.recovery_title_hero),
                    fontSize = 38.sp,
                    lineHeight = 42.sp,
                    fontWeight = FontWeight.ExtraBold,
                )

                Spacer(modifier = Modifier.height(30.dp))

                CustomOutlinedTextField(
                    value = "",
                    onValueChange = {  },
                    placeholderText = stringResource(R.string.recovery_hint_email),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
//                    isError = validationState.getError(FIELD_EMAIL) != null,
//                    supportingText = {
//                        validationState.getError(FIELD_EMAIL)?.let {
//                            Text(it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
//                        }
//                    },
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {  },
//                    enabled = isFormValid && registerState !is UIState.Loading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                ) {

//                    if (registerState is UIState.Loading) {
//                        StaggeredDotsWave(
//                            dotColor = MaterialTheme.colorScheme.onPrimaryFixed,
//                            delayBetweenDots = 300
//                        )
//                    } else {
                        Text(
                            stringResource(R.string.recovery_recovery_button),
                            color = MaterialTheme.colorScheme.onPrimaryFixed
                        )
//                    }

                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen()
}