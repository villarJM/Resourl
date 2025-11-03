package com.devvillar.resourl.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, name = "OTP Vacío")
@Composable
private fun OtpTextFieldEmptyPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column {
                Text("OTP Vacío", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                var otpCode by remember { mutableStateOf("") }
                OtpTextField(
                    value = otpCode,
                    onValueChange = { otpCode = it },
                    modifier = Modifier.fillMaxWidth(),
                    otpLength = 6
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "OTP Parcial")
@Composable
private fun OtpTextFieldPartialPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column {
                Text("OTP Parcial (3 dígitos)", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                var otpCode by remember { mutableStateOf("123") }
                OtpTextField(
                    value = otpCode,
                    onValueChange = { otpCode = it },
                    modifier = Modifier.fillMaxWidth(),
                    otpLength = 6
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "OTP Completo")
@Composable
private fun OtpTextFieldCompletePreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column {
                Text("OTP Completo (6 dígitos)", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                var otpCode by remember { mutableStateOf("123456") }
                OtpTextField(
                    value = otpCode,
                    onValueChange = { otpCode = it },
                    modifier = Modifier.fillMaxWidth(),
                    otpLength = 6
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "OTP con Error")
@Composable
private fun OtpTextFieldErrorPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column {
                Text("OTP con Error", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                var otpCode by remember { mutableStateOf("1234") }
                OtpTextField(
                    value = otpCode,
                    onValueChange = { otpCode = it },
                    modifier = Modifier.fillMaxWidth(),
                    otpLength = 6,
                    isError = true
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "OTP Deshabilitado")
@Composable
private fun OtpTextFieldDisabledPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column {
                Text("OTP Deshabilitado", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                var otpCode by remember { mutableStateOf("12") }
                OtpTextField(
                    value = otpCode,
                    onValueChange = { otpCode = it },
                    modifier = Modifier.fillMaxWidth(),
                    otpLength = 6,
                    enabled = false
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "OTP 4 Dígitos")
@Composable
private fun OtpTextField4DigitsPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column {
                Text("OTP 4 Dígitos", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                var otpCode by remember { mutableStateOf("12") }
                OtpTextField(
                    value = otpCode,
                    onValueChange = { otpCode = it },
                    modifier = Modifier.fillMaxWidth(),
                    otpLength = 4
                )
            }
        }
    }
}

