package com.devvillar.resourl.features.auth.presentation.events

sealed class ForgotPasswordEvent {
    data class OnEmailChange(val email: String) : ForgotPasswordEvent()
    object OnRecoveryClick : ForgotPasswordEvent()
    object OnLoginClick : ForgotPasswordEvent()
}