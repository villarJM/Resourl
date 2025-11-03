package com.devvillar.resourl.features.auth.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.devvillar.resourl.core.base.BaseViewModel
import com.devvillar.resourl.core.network.ApiResponse
import com.devvillar.resourl.core.state.UIState
import com.devvillar.resourl.core.utils.ValidationAuth
import com.devvillar.resourl.core.utils.ValidationResult
import com.devvillar.resourl.features.auth.data.datasources.remote.request.RegisterRequest
import com.devvillar.resourl.features.auth.domain.usecases.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val validationAuth: ValidationAuth
) : BaseViewModel() {

    private val _registerUIState = MutableStateFlow<UIState<ApiResponse<Nothing>>>(UIState.Initial)
    val registerUIState: MutableStateFlow<UIState<ApiResponse<Nothing>>> = _registerUIState

    private val _validationResult = MutableStateFlow(ValidationResult())
    val validationResult: StateFlow<ValidationResult> = _validationResult.asStateFlow()

    private val _firstName = MutableStateFlow("")
    val firstName: StateFlow<String> = _firstName.asStateFlow()

    private val _lastName = MutableStateFlow("")
    val lastName: StateFlow<String> = _lastName.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()

    fun onFirstNameChanged(firstName: String) {
        clearValidationResults()
        _firstName.value = firstName
    }

    fun onLastNameChanged(lastName: String) {
        clearValidationResults()
        _lastName.value = lastName
    }

    fun onEmailChanged(email: String) {
        clearValidationResults()
        _email.value = email
    }

    fun onPasswordChanged(password: String) {
        clearValidationResults()
        _password.value = password
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        clearValidationResults()
        _confirmPassword.value = confirmPassword
    }

    val isFormValid: StateFlow<Boolean> = combine(_firstName, _lastName, _email, _password, _confirmPassword) { firstName, lastName, email, password, confirmPassword ->
        validationAuth.validateFieldRegistrations(firstName, lastName, email, password, confirmPassword).isValid
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun register() {

        val validationResult = validationAuth.validateFieldRegistrations(_firstName.value, _lastName.value, _email.value, _password.value, _confirmPassword.value)
        if (!validationResult.isValid) {
            _validationResult.value = validationResult
            return
        }

        val registerRequest = RegisterRequest(
            firstName = _firstName.value,
            lastName = _lastName.value,
            email = _email.value,
            password = _password.value
        )
        viewModelScope.launch(Dispatchers.IO) {

            _registerUIState.value = UIState.Loading

            val result = registerUseCase(registerRequest)
            result.fold(
                onSuccess = { apiResponse ->
                    _registerUIState.value = UIState.Success(apiResponse)
                },
                onFailure = { error ->
                    _registerUIState.value = UIState.Error(error.message.orEmpty())
                }
            )
        }
    }

    fun clearValidationResults() {
        _validationResult.value = ValidationResult()
    }
}