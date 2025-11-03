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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devvillar.resourl.core.ui.components.OtpTextField

@Composable
fun AccountVerificationScreen(
    onNavigateToLogin: () -> Unit = {},
    onNavigateToResetPassword: () -> Unit = {}
) {
    val snackBarHostState = remember { SnackbarHostState() }
    var otpCode by remember { mutableStateOf("") }

//    val forgotPasswordState by viewModel.forgotPasswordUIState.collectAsStateWithLifecycle()
//    val validationState by viewModel.validationResult.collectAsStateWithLifecycle()
//
//    val email by viewModel.email.collectAsStateWithLifecycle()
//
//    val isFormValid = viewModel.isFormValid.collectAsStateWithLifecycle()

//    LaunchedEffect(forgotPasswordState) {
//        when (forgotPasswordState) {
//            is UIState.Success -> onNavigateToLogin()
//            is UIState.Error -> {
//                snackBarHostState.showSnackbar((forgotPasswordState as UIState.Error).message)
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
                    onClick = { onNavigateToResetPassword()  }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    "Account Verification",
                    fontSize = 38.sp,
                    lineHeight = 42.sp,
                    fontWeight = FontWeight.ExtraBold,
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    "Enter the 6-digit code sent to your email address",
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(40.dp))

                OtpTextField(
                    value = otpCode,
                    onValueChange = { otpCode = it },
                    modifier = Modifier.fillMaxWidth(),
                    otpLength = 6,
//                    isError = validationState.getError(FIELD_OTP) != null
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {  },
//                    enabled = isFormValid.value && forgotPasswordState !is UIState.Loading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                ) {

//                    if (forgotPasswordState is UIState.Loading) {
//                        StaggeredDotsWave(
//                            dotColor = MaterialTheme.colorScheme.onPrimaryFixed,
//                            delayBetweenDots = 300
//                        )
//                    } else {
                        Text(
                            "Verificar Código",
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
fun AccountVerificationScreenPreview() {
    AccountVerificationScreen()
}