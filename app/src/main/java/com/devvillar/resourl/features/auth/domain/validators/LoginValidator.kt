package com.devvillar.resourl.features.auth.domain.validators

import com.devvillar.resourl.core.validator.base.FieldValidationState
import com.devvillar.resourl.core.validator.base.FieldValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface LoginValidator {
    val state: StateFlow<LoginValidationState>
    fun onEmailChanged(value: String)
    fun onPasswordChanged(value: String)
}