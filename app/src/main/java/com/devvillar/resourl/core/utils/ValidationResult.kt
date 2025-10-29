package com.devvillar.resourl.core.utils

data class ValidationResult(
    val errors: Map<String, String> = emptyMap()
) {
    val isValid: Boolean get() = errors.isEmpty()
    fun hasError(field: String): Boolean = errors.containsKey(field)
    fun getError(field: String): String? = errors[field]
}