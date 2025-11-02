package com.devvillar.resourl.core.utils

import com.devvillar.resourl.core.utils.ValidationFormField.FIELD_CONFIRM_PASSWORD
import com.devvillar.resourl.core.utils.ValidationFormField.FIELD_EMAIL
import com.devvillar.resourl.core.utils.ValidationFormField.FIELD_FIRST_NAME
import com.devvillar.resourl.core.utils.ValidationFormField.FIELD_LAST_NAME
import com.devvillar.resourl.core.utils.ValidationFormField.FIELD_PASSWORD
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidationAuth @Inject constructor(
    private val validationUtils: ValidationUtils
) {

    /**
     * Validates both email and password and returns all errors found
     * Shows both errors when both fields are invalid
     */
    fun validateFieldLogins(email: String?, password: String?): ValidationResult {
        val errors = mutableMapOf<String, String>()
        // Validate both fields to get all errors
        validationUtils.validateFieldEmail(email)?.let { errors[FIELD_EMAIL] = it }
        validationUtils.validateFieldPassword(password)?.let { errors[FIELD_PASSWORD] = it }

        // Return all errors found, not just the first one
        return ValidationResult(errors)
    }

    /**
     * Validates email, password, and confirm password for registration
     * Ensures all fields are checked and returns all errors found
     */
    fun validateFieldRegistrations(firstName: String?, lastName: String?, email: String?, password: String?, confirmPassword: String?): ValidationResult {
        val errors = mutableMapOf<String, String>()
        // Validate email and password to get all errors
        validationUtils.validateFieldFirstName(firstName)?.let { errors[FIELD_FIRST_NAME] = it }
        validationUtils.validateFieldLastName(lastName)?.let { errors[FIELD_LAST_NAME] = it }
        validationUtils.validateFieldEmail(email)?.let { errors[FIELD_EMAIL] = it }
        validationUtils.validateFieldPassword(password)?.let { errors[FIELD_PASSWORD] = it }
        validationUtils.validateFieldConfirmPassword(password, confirmPassword)?.let { errors[FIELD_CONFIRM_PASSWORD] = it }
        // Return all errors found
        return ValidationResult(errors)
    }
}