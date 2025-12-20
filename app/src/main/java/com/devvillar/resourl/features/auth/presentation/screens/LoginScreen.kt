package com.devvillar.resourl.features.auth.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devvillar.resourl.R
import com.devvillar.resourl.core.ui.components.CustomOutlinedTextField
import com.devvillar.resourl.core.ui.components.StaggeredDotsWave
import com.devvillar.resourl.features.auth.presentation.events.LoginEvent
import com.devvillar.resourl.features.auth.presentation.states.LoginUIState


@Composable
fun LoginScreen(
    state: LoginUIState,
    snackBarHostState: SnackbarHostState,
    onEvent: (LoginEvent) -> Unit,
) {

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
                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    stringResource(R.string.login_title_hero_1),
                    style = MaterialTheme.typography.headlineLarge
                )

                Spacer(modifier = Modifier.height(30.dp))

                CustomOutlinedTextField(
                    value = state.email,
                    onValueChange = { onEvent(LoginEvent.OnEmailChange(it)) },
                    placeholderText = stringResource(R.string.login_hint_email),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    isError = state.emailError != null,
                    supportingText = {
                        Text(state.emailError.orEmpty(), color = MaterialTheme.colorScheme.error)
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomOutlinedTextField(
                    value = state.password,
                    onValueChange = { onEvent(LoginEvent.OnPasswordChange(it)) },
                    placeholderText = stringResource(R.string.login_hint_password),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),  
                    isPassword = true,
                    isError = state.passwordError != null,
                    supportingText = {
                        Text(state.passwordError.orEmpty(), color = MaterialTheme.colorScheme.error)
                    },
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextButton(
                    onClick = { onEvent(LoginEvent.OnForgotPasswordClick) }
                ) {
                    Text(
                        stringResource(R.string.login_forgot_password_button),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { onEvent(LoginEvent.OnLoginClick) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    enabled = state.isFormValid && state.isLoading.not()
                ) {

                    if (state.isLoading) {
                        StaggeredDotsWave(
                            dotColor = MaterialTheme.colorScheme.onPrimaryFixed,
                            delayBetweenDots = 300
                        )
                    } else {
                        Text(
                            stringResource(R.string.login_login_button),
                            color = MaterialTheme.colorScheme.onPrimaryFixed
                        )
                    }

                }

                Spacer(modifier = Modifier.height(30.dp))

                OutlinedButton(
                    onClick = { onEvent(LoginEvent.OnRegisterClick) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                ) {
                    Text(
                        stringResource(R.string.login_register_button),
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen(
        state = LoginUIState(),
        snackBarHostState = SnackbarHostState(),
        onEvent = {}

    )
}