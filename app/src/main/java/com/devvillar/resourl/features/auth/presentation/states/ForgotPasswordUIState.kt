package com.devvillar.resourl.features.auth.presentation.states

data class ForgotPasswordUIState(
    val email: String = "",
    val isFormValid: Boolean = false,
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val forgotPasswordError: String? = null
)
