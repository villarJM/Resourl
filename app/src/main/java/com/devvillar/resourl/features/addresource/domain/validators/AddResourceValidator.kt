package com.devvillar.resourl.features.addresource.domain.validators

import kotlinx.coroutines.flow.StateFlow

interface AddResourceValidator {
    val state: StateFlow<AddResourceValidationState>
    fun onUrlChanged(url: String)
}