package com.devvillar.resourl.features.auth.presentation.effects

sealed class ForgotPasswordEffect {
    object NavigateToAccountVerification : ForgotPasswordEffect()
    object NavigateToLogin : ForgotPasswordEffect()
    data class ShowError(val message: String) : ForgotPasswordEffect()

}