package com.devvillar.resourl.core.utils

import android.content.Context
import android.util.Patterns
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Utility for input field validations
 * Injectable class that handles validation logic
 */
@Singleton
class ValidationUtils @Inject constructor(
    @param:ApplicationContext private val context: Context
) {

    // Constants for better maintainability
    companion object {
        const val MIN_PASSWORD_LENGTH = 8
        val SPECIAL_CHARACTERS = setOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '-', '=', '[', ']', '{', '}', '|', ';', ':', ',', '.', '<', '>', '?')

        const val FIELD_EMAIL = "email"
        const val FIELD_PASSWORD = "password"
        const val FIELD_CONFIRM_PASSWORD = "confirmPassword"
    }

    // Error messages using string resources - Context already available
    private val emailEmptyError: String = "Email cannot be empty"
    private val emailInvalidError: String = "Invalid email format"
    private val passwordEmptyError: String = "Password cannot be empty"
    private val passwordInvalidError: String = "Password must be at least $MIN_PASSWORD_LENGTH characters long and include uppercase, lowercase, digit, and special character"
    private val confirmPasswordEmptyError: String = "Confirm Password cannot be empty"
    private val confirmPasswordMismatchError: String = "Passwords do not match"

    /**
     * Validates both email and password and returns all errors found
     * Shows both errors when both fields are invalid
     */
    fun validateFieldLogins(email: String?, password: String?): ValidationResult {
        val errors = mutableMapOf<String, String>()
        // Validate both fields to get all errors
        validateEmail(email)?.let { errors[FIELD_EMAIL] = it }
        validatePassword(password)?.let { errors[FIELD_PASSWORD] = it }

        // Return all errors found, not just the first one
        return ValidationResult(errors)
    }

    /**
     * Validates email, password, and confirm password for registration
     * Ensures all fields are checked and returns all errors found
     */
    fun validateFieldRegistrations(email: String?, password: String?, confirmPassword: String?): ValidationResult {
        val errors = mutableMapOf<String, String>()
        // Validate email and password to get all errors
        validateEmail(email)?.let { errors[FIELD_EMAIL] = it }
        validatePassword(password)?.let { errors[FIELD_PASSWORD] = it }
        validateConfirmPassword(password, confirmPassword)?.let { errors[FIELD_CONFIRM_PASSWORD] = it }
        // Return all errors found
        return ValidationResult(errors)
    }

    /**
     * Validates email format
     * @return error message or null if valid
     */
    fun validateEmail(email: String?): String? = when {
        email.isNullOrBlank() -> emailEmptyError
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> emailInvalidError
        else -> null
    }

    /**
     * Validates password strength
     * @return error message or null if valid
     */
    fun validatePassword(password: String?): String? = when {
        password.isNullOrBlank() -> passwordEmptyError
        !isValidPassword(password) -> passwordInvalidError
        else -> null
    }

    /**
     * Validates that confirm password matches the original password
     * @return error message or null if valid
     */
    private fun validateConfirmPassword(password: String?, confirmPassword: String?): String? = when {
        confirmPassword.isNullOrBlank() -> confirmPasswordEmptyError
        password != confirmPassword -> confirmPasswordMismatchError
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
}