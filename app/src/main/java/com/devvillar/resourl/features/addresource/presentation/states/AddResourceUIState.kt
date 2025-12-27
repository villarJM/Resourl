package com.devvillar.resourl.features.addresource.presentation.states

import com.devvillar.resourl.features.addresource.domain.ResourceItem

data class AddResourceUIState(
    val url: String = "",
    val urlError: String? = null,
    val listResourceItems: List<ResourceItem> = emptyList(),
    val isLoadingAdd: Boolean = false,
    val isLoadingItems: Boolean = false,
    val isFormValid: Boolean = false,
    val addResourceError: String? = null
)
