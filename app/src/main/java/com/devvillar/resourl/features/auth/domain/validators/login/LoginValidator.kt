package com.devvillar.resourl.features.auth.domain.validators.login

import kotlinx.coroutines.flow.StateFlow

interface LoginValidator {
    val state: StateFlow<LoginValidationState>
    fun onEmailChanged(value: String)
    fun onPasswordChanged(value: String)
}