package com.devvillar.resourl.features.addresource.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devvillar.resourl.features.addresource.domain.ResourceItem
import com.devvillar.resourl.features.addresource.domain.validators.AddResourceValidator
import com.devvillar.resourl.features.addresource.presentation.effects.AddResourceEffect
import com.devvillar.resourl.features.addresource.presentation.events.AddResourceEvent
import com.devvillar.resourl.features.addresource.presentation.states.AddResourceUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class AddResourceViewModel(
    private val validator: AddResourceValidator
) : ViewModel() {

    companion object {
        private const val TAG = "AddResourceViewModel"
    }

    private val _uiState = MutableStateFlow(AddResourceUIState())
    val uiState: StateFlow<AddResourceUIState> = _uiState

    init {
        onEvent(AddResourceEvent.OnObserveValidation)
        onEvent(AddResourceEvent.GetResourceItems)
    }

    private val _effect = Channel<AddResourceEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: AddResourceEvent) {
        when (event) {
            is AddResourceEvent.OnUrlChange -> onUrlChange(event.url)
            is AddResourceEvent.OnAddResourceClick -> addResource()
            is AddResourceEvent.OnEditResourceClick -> viewModelScope.launch { _effect.send(AddResourceEffect.NavigateToEditResource) }
            is AddResourceEvent.OnSearchResourceClick -> viewModelScope.launch { _effect.send(AddResourceEffect.NavigateToSearchResource) }
            is AddResourceEvent.OnDeleteResourceClick -> {/*Open delete dialog*/}
            is AddResourceEvent.GetResourceItems -> getResourceItems()
            is AddResourceEvent.OnObserveValidation -> observeValidation()
        }
    }

    fun onUrlChange(newUrl: String) {
        Timber.tag(TAG).d("onUrlChange: $newUrl")
        _uiState.update { it.copy(url = newUrl) }
        validator.onUrlChanged(newUrl)
    }

    fun addResource() {

        if (!_uiState.value.isFormValid) {
            Timber.tag(TAG).d("onLoginClick: Form is not valid")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {

            Timber.tag(TAG).d("UIState is Loading in login()")
        }
    }

    private fun getResourceItems() {
        val mockItems = listOf(
            ResourceItem(
                id = 1,
                title = "Kotlin Documentation",
                url = "https://kotlinlang.org/docs/home.html",
                description = "Official Kotlin programming language documentation.",
                category = "Programming",
                tags = listOf("Kotlin", "Documentation", "Programming"),
                date = "15, January 2023"
            ),
            ResourceItem(
                id = 2,
                title = "Android Developers",
                url = "https://developer.android.com/",
                description = "Official site for Android app development.",
                category = "Mobile Development",
                tags = listOf("Android", "Development", "Mobile"),
                date = "20, February 2023"
            ),
            ResourceItem(
                id = 3,
                title = "Jetpack Compose",
                url = "https://developer.android.com/jetpack/compose",
                description = "Modern toolkit for building native Android UI.",
                category = "UI Framework",
                tags = listOf("Jetpack", "Compose", "UI"),
                date = "10, March 2023"
            ),
            ResourceItem(
                id = 4,
                title = "Material Design 3",
                url = "https://m3.material.io/",
                description = "Google's design system for creating intuitive and beautiful products.",
                category = "Design",
                tags = listOf("Material", "Design", "UI/UX"),
                date = "05, April 2023"
            )



        )
        _uiState.update { it.copy(listResourceItems = mockItems) }
    }

    private fun observeValidation() {
        Timber.tag(TAG).d("observeValidation")
        viewModelScope.launch {
            validator.state.collect { state ->
                Timber.tag(TAG).d("observeValidation: $state")
                _uiState.update {
                    it.copy(
                        urlError = state.url.errorMessage,
                        isFormValid = state.isFormValid
                    )
                }
            }
        }
    }
}