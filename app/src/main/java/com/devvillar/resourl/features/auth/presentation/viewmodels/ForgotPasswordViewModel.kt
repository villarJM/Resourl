package com.devvillar.resourl.features.auth.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devvillar.resourl.core.base.BaseViewModel
import com.devvillar.resourl.core.network.ApiResponse
import com.devvillar.resourl.core.state.UIState
import com.devvillar.resourl.core.utils.ValidationAuth
import com.devvillar.resourl.core.utils.ValidationResult
import com.devvillar.resourl.features.auth.domain.usecases.ForgotPasswordUseCase
import com.devvillar.resourl.features.auth.domain.validators.forgotpassword.ForgotPasswordValidator
import com.devvillar.resourl.features.auth.presentation.effects.ForgotPasswordEffect
import com.devvillar.resourl.features.auth.presentation.effects.LoginEffect
import com.devvillar.resourl.features.auth.presentation.events.ForgotPasswordEvent
import com.devvillar.resourl.features.auth.presentation.states.ForgotPasswordUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val validator: ForgotPasswordValidator
) : ViewModel() {

    companion object {
        private const val TAG = "ForgotPasswordViewModel"
    }

    private val _uiState = MutableStateFlow(ForgotPasswordUIState())
    val uiState: StateFlow<ForgotPasswordUIState> = _uiState

    init {
        onEvent(ForgotPasswordEvent.OnObserveValidation)
    }

    private val _effect = Channel<ForgotPasswordEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: ForgotPasswordEvent) {
        when (event) {
            is ForgotPasswordEvent.OnEmailChange -> onEmailChange(event.email)
            is ForgotPasswordEvent.OnRecoveryClick -> forgotPassword()
            is ForgotPasswordEvent.OnLoginClick -> viewModelScope.launch { _effect.send(ForgotPasswordEffect.NavigateToLogin) }
            is ForgotPasswordEvent.OnObserveValidation -> observeValidation()
        }
    }

    fun onEmailChange(newEmail: String) {
        Timber.tag(TAG).d("onEmailChange: $newEmail")
        _uiState.update { it.copy(email = newEmail) }
        validator.onEmailChanged(newEmail)
    }

    fun forgotPassword() {

        if (!_uiState.value.isFormValid) {
            Timber.tag(TAG).d("onLoginClick: Form is not valid")
            return
        }

        viewModelScope.launch(Dispatchers.IO) {

            Timber.tag(TAG).d("UIState is Loading in login()")
            _uiState.update { it.copy(isLoading = true) }

            val result = forgotPasswordUseCase(_uiState.value.email)
            result.fold(
                onSuccess = { apiResponse ->
                    Timber.tag(TAG).d("UIState is Success in login(): $apiResponse")
                    _uiState.update { it.copy(isLoading = false) }
                    _effect.send(ForgotPasswordEffect.NavigateToLogin)
                },
                onFailure = { error ->
                    Timber.tag(TAG).d("UIState is Error in login(): $error")
                    _effect.send(ForgotPasswordEffect.ShowError(error.message.orEmpty()))
                }
            )
        }

    }

    private fun observeValidation() {
        Timber.tag(TAG).d("observeValidation")
        viewModelScope.launch {
            validator.state.collect { state ->
                Timber.tag(TAG).d("observeValidation: $state")
                _uiState.update {
                    it.copy(
                        emailError = state.email.errorMessage,
                        isFormValid = state.isFormValid
                    )
                }
            }
        }
    }
}