package com.devvillar.resourl.features.addresource.domain.validators

import com.devvillar.resourl.core.validator.base.FieldValidationState

data class AddResourceValidationState(
    val url: FieldValidationState = FieldValidationState(),
    val isFormValid: Boolean = false
)
