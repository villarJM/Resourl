package com.devvillar.resourl.features.auth.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devvillar.resourl.core.base.BaseViewModel
import com.devvillar.resourl.core.network.ApiResponse
import com.devvillar.resourl.core.state.UIState
import com.devvillar.resourl.core.utils.ValidationAuth
import com.devvillar.resourl.core.utils.ValidationResult
import com.devvillar.resourl.features.auth.domain.usecases.ForgotPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val validationAuth: ValidationAuth
) : BaseViewModel() {

    private val _forgotPasswordUIState = MutableStateFlow<UIState<ApiResponse<Nothing>>>(UIState.Initial)
    val forgotPasswordUIState: StateFlow<UIState<ApiResponse<Nothing>>> = _forgotPasswordUIState.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _validationResult = MutableStateFlow(ValidationResult())
    val validationResult: StateFlow<ValidationResult> = _validationResult.asStateFlow()

    val isFormValid: StateFlow<Boolean> = _email.map { email ->
        validationAuth.validateFieldForgotPassword(email).isValid
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)


    fun onEmailChange(newEmail: String) {
        clearValidationResults()
        _email.value = newEmail
    }

    fun forgotPassword() {

        val validationResult = validationAuth.validateFieldForgotPassword(_email.value)

        if (_validationResult.value.isValid) {
            _validationResult.value = validationResult
            return
        }

        viewModelScope.launch(Dispatchers.IO) {

            _forgotPasswordUIState.value = UIState.Loading

            val result = forgotPasswordUseCase(_email.value)
            result.fold(
                onSuccess = { apiResponse ->
                    _forgotPasswordUIState.value = UIState.Success(apiResponse)
                },
                onFailure = { error ->
                    _forgotPasswordUIState.value = UIState.Error(error.message.orEmpty())
                }
            )
        }

    }


    fun clearValidationResults() {
        _validationResult.value = ValidationResult()
    }
}