package com.devvillar.resourl.features.addresource.domain.validators

import com.devvillar.resourl.core.validator.UrlValidator
import com.devvillar.resourl.core.validator.base.FieldValidationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AddResourceValidatorImpl(
    private val urlValidator: UrlValidator,
): AddResourceValidator {

    private val _state = MutableStateFlow(AddResourceValidationState())
    override val state: StateFlow<AddResourceValidationState> = _state

    override fun onUrlChanged(url: String) {
        update(url = urlValidator.validate(url))
    }

    private fun update(
        url: FieldValidationState = _state.value.url,
    ) {
        val isFormValid = listOf(url.isValid).all { it }
        _state.value = AddResourceValidationState(url, isFormValid)
    }

}