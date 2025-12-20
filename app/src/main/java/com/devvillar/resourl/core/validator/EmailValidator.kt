package com.devvillar.resourl.core.validator

import android.util.Patterns
import com.devvillar.resourl.core.validator.base.FieldValidationState
import com.devvillar.resourl.core.validator.base.FieldValidator

class EmailValidator : FieldValidator<String> {
    override fun validate(value: String?): FieldValidationState = when {
        value.isNullOrBlank() -> FieldValidationState(isValid = false, errorMessage = "Email cannot be empty")
        !Patterns.EMAIL_ADDRESS.matcher(value).matches() -> FieldValidationState(isValid = false, errorMessage = "Invalid email format")
        else -> FieldValidationState(isValid = true)
    }
}