package com.devvillar.resourl.core.validator

import com.devvillar.resourl.core.validator.base.FieldValidationState
import com.devvillar.resourl.core.validator.base.FieldValidator

class PasswordValidator : FieldValidator<String> {

    companion object {
        const val MIN_PASSWORD_LENGTH = 8
        val SPECIAL_CHARACTERS = setOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '-', '=', '[', ']', '{', '}', '|', ';', ':', ',', '.', '<', '>', '?')
    }

    override fun validate(value: String?): FieldValidationState = when {
        value.isNullOrBlank() -> FieldValidationState(isValid = false, errorMessage = "Password cannot be empty")
        !isValidPassword(value) -> FieldValidationState(isValid = false, errorMessage = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
        else -> FieldValidationState(isValid = true)
    }

    fun isValidPassword(password: String?): Boolean {
        if (password == null || password.length < MIN_PASSWORD_LENGTH) return false

        var hasUpper = false
        var hasLower = false
        var hasDigit = false
        var hasSpecial = false

        // Single iteration over the string for better performance
        for (char in password) {
            when {
                char.isUpperCase() -> hasUpper = true
                char.isLowerCase() -> hasLower = true
                char.isDigit() -> hasDigit = true
                char in SPECIAL_CHARACTERS -> hasSpecial = true
            }
            // Early exit if all criteria are already met
            if (hasUpper && hasLower && hasDigit && hasSpecial) return true
        }

        return false
    }
}