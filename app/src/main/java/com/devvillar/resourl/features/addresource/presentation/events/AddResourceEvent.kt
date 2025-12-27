package com.devvillar.resourl.features.addresource.presentation.events

sealed class AddResourceEvent {
    data class OnUrlChange(val url: String) : AddResourceEvent()
    object OnAddResourceClick : AddResourceEvent()
    object OnSearchResourceClick : AddResourceEvent()
    object OnEditResourceClick : AddResourceEvent()
    object OnDeleteResourceClick : AddResourceEvent()
    object GetResourceItems : AddResourceEvent()
    object OnObserveValidation : AddResourceEvent()
}