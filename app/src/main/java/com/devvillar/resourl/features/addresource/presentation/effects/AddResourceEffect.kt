package com.devvillar.resourl.features.addresource.presentation.effects

sealed class AddResourceEffect {
    object NavigateToEditResource : AddResourceEffect()
    object NavigateToSearchResource : AddResourceEffect()
    data class ShowError(val message: String) : AddResourceEffect()
}
