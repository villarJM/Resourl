package com.devvillar.resourl.features.auth.domain.validators.forgotpassword

import com.devvillar.resourl.core.validator.base.FieldValidationState

data class ForgotPasswordValidationState(
    val email: FieldValidationState = FieldValidationState(),
    val isFormValid: Boolean = false
)
