package com.devvillar.resourl.features.auth.presentation.effects

sealed class LoginEffect {
    object NavigateToRegister : LoginEffect()
    object NavigateToForgotPassword : LoginEffect()
    object NavigateToHome : LoginEffect()
    data class ShowError(val message: String) : LoginEffect()
}