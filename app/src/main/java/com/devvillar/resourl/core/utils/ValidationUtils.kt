package com.devvillar.resourl.core.utils

import android.content.Context
import android.util.Patterns
import com.devvillar.resourl.core.utils.ValidationFormField.FIELD_CONFIRM_PASSWORD
import com.devvillar.resourl.core.utils.ValidationFormField.FIELD_EMAIL
import com.devvillar.resourl.core.utils.ValidationFormField.FIELD_PASSWORD
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Utility for input field validations
 * Injectable class that handles validation logic
 */
@Singleton
class ValidationUtils @Inject constructor(
    private val validationErrorMessages: ValidationErrorMessages
) {

    // Constants for better maintainability
    companion object {
        const val MIN_PASSWORD_LENGTH = 8
        const val OTP_CODE_LENGTH = 6
        val SPECIAL_CHARACTERS = setOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '-', '=', '[', ']', '{', '}', '|', ';', ':', ',', '.', '<', '>', '?')

    }

    /**
     * Validates email format
     * @return error message or null if valid
     */
    fun validateFieldEmail(email: String?): String? = when {
        email.isNullOrBlank() -> validationErrorMessages.emailEmptyError
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> validationErrorMessages.emailInvalidError
        else -> null
    }

    /**
     * Validates password strength
     * @return error message or null if valid
     */
    fun validateFieldPassword(password: String?): String? = when {
        password.isNullOrBlank() -> validationErrorMessages.passwordEmptyError
        !isValidPassword(password) -> validationErrorMessages.passwordInvalidError
        else -> null
    }

    /**
     * Validates that confirm password matches the original password
     * @return error message or null if valid
     */
    fun validateFieldConfirmPassword(password: String?, confirmPassword: String?): String? = when {
        confirmPassword.isNullOrBlank() -> validationErrorMessages.confirmPasswordEmptyError
        password != confirmPassword -> validationErrorMessages.confirmPasswordMismatchError
        else -> null
    }

    /**
     * Checks if a password meets security criteria
     * Optimized for lazy evaluation
     */
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

    fun validateFieldFirstName(firstName: String?): String? = when {
        firstName.isNullOrBlank() -> validationErrorMessages.firstNameEmptyError
        firstName != firstName.trim() -> validationErrorMessages.firstNameWhitespaceError
        else -> null
    }

    fun validateFieldLastName(lastName: String?): String? = when {
        lastName.isNullOrBlank() -> validationErrorMessages.lastNameEmptyError
        lastName != lastName.trim() -> validationErrorMessages.lastNameWhitespaceError
        else -> null
    }

    fun validateFieldOTPCode(otpCode: String?): String? = when {
        otpCode.isNullOrBlank() -> validationErrorMessages.otpCodeEmptyError
        !otpCode.all { it.isDigit() } -> validationErrorMessages.otpCodeInvalidError
        otpCode.length != OTP_CODE_LENGTH -> validationErrorMessages.otpCodeLengthError
        else -> null
    }
}