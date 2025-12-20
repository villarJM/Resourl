package com.devvillar.resourl.features.auth.presentation.states

data class LoginUIState (
    val email: String = "",
    val password: String = "",
    val isFormValid: Boolean = false,
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val loginError: String? = null
)