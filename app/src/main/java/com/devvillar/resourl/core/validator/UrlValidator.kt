package com.devvillar.resourl.core.validator

import android.util.Patterns
import com.devvillar.resourl.core.validator.base.FieldValidationState
import com.devvillar.resourl.core.validator.base.FieldValidator

class UrlValidator : FieldValidator<String> {
    override fun validate(value: String?): FieldValidationState = when {
        value.isNullOrBlank() -> FieldValidationState(isValid = false, errorMessage = "URL cannot be empty")
        !isValidUrl(value) -> FieldValidationState(isValid = false, errorMessage = "Invalid URL")
        else -> FieldValidationState(isValid = true)
    }

    private fun isValidUrl(url: String): Boolean {
        val trimmedUrl = url.trim()
        return Patterns.WEB_URL.matcher(trimmedUrl).matches()
    }
}