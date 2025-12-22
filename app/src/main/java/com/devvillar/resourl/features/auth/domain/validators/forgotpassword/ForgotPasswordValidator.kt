package com.devvillar.resourl.features.auth.domain.validators.forgotpassword

import kotlinx.coroutines.flow.StateFlow

interface ForgotPasswordValidator {
    val state: StateFlow<ForgotPasswordValidationState>
    fun onEmailChanged(value: String)
}