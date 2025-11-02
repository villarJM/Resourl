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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.devvillar.resourl.R
import com.devvillar.resourl.core.network.ApiResponse
import com.devvillar.resourl.core.state.UIState
import com.devvillar.resourl.core.ui.components.CustomOutlinedTextField
import com.devvillar.resourl.core.ui.components.StaggeredDotsWave
import com.devvillar.resourl.core.utils.ValidationFormField.FIELD_CONFIRM_PASSWORD
import com.devvillar.resourl.core.utils.ValidationFormField.FIELD_EMAIL
import com.devvillar.resourl.core.utils.ValidationFormField.FIELD_FIRST_NAME
import com.devvillar.resourl.core.utils.ValidationFormField.FIELD_LAST_NAME
import com.devvillar.resourl.core.utils.ValidationFormField.FIELD_PASSWORD
import com.devvillar.resourl.core.utils.ValidationResult
import com.devvillar.resourl.features.auth.presentation.viewmodels.RegisterViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit = {},
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val registerState by viewModel.registerUIState.collectAsStateWithLifecycle()
    val validationState by viewModel.validationResult.collectAsStateWithLifecycle()


    val firstName by viewModel.firstName.collectAsStateWithLifecycle()
    val lastName by viewModel.lastName.collectAsStateWithLifecycle()
    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val confirmPassword by viewModel.confirmPassword.collectAsStateWithLifecycle()

    val isFormValid by viewModel.isFormValid.collectAsStateWithLifecycle()


    LaunchedEffect(registerState) {
        when (registerState) {
            is UIState.Success -> onNavigateToLogin()
            is UIState.Error -> {
                snackBarHostState.showSnackbar((registerState as UIState.Error).message)
            }
            else -> {}
        }
    }

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

                Spacer(modifier = Modifier.height(56.dp))

                Text(
                    stringResource(R.string.register_title_hero),
                    fontSize = 38.sp,
                    lineHeight = 42.sp,
                    fontWeight = FontWeight.ExtraBold,
                )

                Spacer(modifier = Modifier.height(30.dp))

                CustomOutlinedTextField(
                    value = firstName,
                    onValueChange = { viewModel.onFirstNameChanged(it ) },
                    placeholderText = stringResource(R.string.register_hint_name),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    isError = validationState.getError(FIELD_FIRST_NAME) != null,
                    supportingText = {
                        validationState.getError(FIELD_FIRST_NAME)?.let {
                            Text(it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
                        }
                    },
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomOutlinedTextField(
                    value = lastName,
                    onValueChange = { viewModel.onLastNameChanged(it) },
                    placeholderText = stringResource(R.string.register_hint_lastname),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    isError = validationState.getError(FIELD_LAST_NAME) != null,
                    supportingText = {
                        validationState.getError(FIELD_LAST_NAME)?.let {
                            Text(it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
                        }
                    },
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomOutlinedTextField(
                    value = email,
                    onValueChange = { viewModel.onEmailChanged(it) },
                    placeholderText = stringResource(R.string.register_hint_email),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    isError = validationState.getError(FIELD_EMAIL) != null,
                    supportingText = {
                        validationState.getError(FIELD_EMAIL)?.let {
                            Text(it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
                        }
                    },
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomOutlinedTextField(
                    value = password,
                    onValueChange = { viewModel.onPasswordChanged(it) },
                    placeholderText = stringResource(R.string.register_hint_password),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    isPassword = true,
                    isError = validationState.getError(FIELD_PASSWORD) != null,
                    supportingText = {
                        validationState.getError(FIELD_PASSWORD)?.let {
                            Text(it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
                        }
                    },
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomOutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { viewModel.onConfirmPasswordChanged(it) },
                    placeholderText = stringResource(R.string.register_hint_confirm_password),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    isPassword = true,
                    isError = validationState.getError(FIELD_CONFIRM_PASSWORD) != null,
                    supportingText = {
                        validationState.getError(FIELD_CONFIRM_PASSWORD)?.let {
                            Text(it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
                        }
                    },
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { viewModel.register() },
                    enabled = isFormValid && registerState !is UIState.Loading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                ) {

                    if (registerState is UIState.Loading) {
                        StaggeredDotsWave(
                            dotColor = MaterialTheme.colorScheme.onPrimaryFixed,
                            delayBetweenDots = 300
                        )
                    } else {
                        Text(
                            stringResource(R.string.register_register_button),
                            color = MaterialTheme.colorScheme.onPrimaryFixed
                        )
                    }

                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RegisterScreenPreview() {
    RegisterScreen()
}