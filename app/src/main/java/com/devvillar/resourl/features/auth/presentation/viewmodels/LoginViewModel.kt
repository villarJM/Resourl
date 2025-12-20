package com.devvillar.resourl.features.auth.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devvillar.resourl.features.auth.domain.usecases.LoginUseCase
import com.devvillar.resourl.features.auth.domain.validators.LoginValidator
import com.devvillar.resourl.features.auth.presentation.effects.LoginEffect
import com.devvillar.resourl.features.auth.presentation.events.LoginEvent
import com.devvillar.resourl.features.auth.presentation.states.LoginUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validator: LoginValidator
) : ViewModel() {

    companion object {
        private const val TAG = "LoginViewModel"
    }

    private val _uiState = MutableStateFlow<LoginUIState>(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState

    init {
        observeValidation()
    }

    private val _effect = Channel<LoginEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChange -> onEmailChange(event.email)
            is LoginEvent.OnPasswordChange -> onPasswordChange(event.password)
            is LoginEvent.OnLoginClick -> login()
            is LoginEvent.OnRegisterClick -> viewModelScope.launch { _effect.send(LoginEffect.NavigateToRegister) }
            is LoginEvent.OnForgotPasswordClick -> viewModelScope.launch { _effect.send(LoginEffect.NavigateToForgotPassword) }
        }
    }

    fun onEmailChange(newEmail: String) {
        Timber.tag(TAG).d("onEmailChange: $newEmail")
        _uiState.update { it.copy(email = newEmail) }
        validator.onEmailChanged(newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        Timber.tag(TAG).d("onPasswordChange: $newPassword")
        _uiState.update { it.copy(password = newPassword) }
        validator.onPasswordChanged(newPassword)
    }

    fun login() {

        if (!_uiState.value.isFormValid) {
            Timber.tag(TAG).d("onLoginClick: Form is not valid")
            return
        }

        viewModelScope.launch {

            Timber.tag(TAG).d("UIState is Loading in login()")
            _uiState.update { it.copy(isLoading = true) }

            val result = loginUseCase(_uiState.value.email, _uiState.value.password)
            result.fold(
                onSuccess = { userSession ->
                    Timber.tag(TAG).d("UIState is Success in login(): $userSession")
                    _uiState.update { it.copy(isLoading = false) }
                    _effect.send(LoginEffect.NavigateToHome)
                },
                onFailure = { error ->
                    Timber.tag(TAG).d("UIState is Error in login(): $error")
                    _effect.send(LoginEffect.ShowError(error.message.orEmpty()))
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
                        passwordError = state.password.errorMessage,
                        isFormValid = state.isFormValid
                    )
                }
            }
        }
    }

}