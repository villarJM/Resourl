package com.devvillar.resourl.features.auth.domain.validators

import com.devvillar.resourl.core.validator.base.FieldValidationState

data class LoginValidationState(
    val email: FieldValidationState = FieldValidationState(),
    val password: FieldValidationState = FieldValidationState(),
    val isFormValid: Boolean = false
)
