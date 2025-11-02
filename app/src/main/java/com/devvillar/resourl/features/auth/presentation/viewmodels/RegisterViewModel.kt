package com.devvillar.resourl.features.auth.presentation.viewmodels

import com.devvillar.resourl.core.base.BaseViewModel
import com.devvillar.resourl.core.network.ApiResponse
import com.devvillar.resourl.core.state.UIState
import com.devvillar.resourl.core.utils.ValidationAuth
import com.devvillar.resourl.core.utils.ValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validationAuth: ValidationAuth
) : BaseViewModel() {

    private val _registerUIState = MutableStateFlow<UIState<ApiResponse<Nothing>>>(UIState.Initial)
    val registerUIState: MutableStateFlow<UIState<ApiResponse<Nothing>>> = _registerUIState

    private val _validationResult = MutableStateFlow(ValidationResult())
    val validationResult: StateFlow<ValidationResult> = _validationResult.asStateFlow()

    fun register(firstName: String, lastName: String, email: String, password: String, confirmPassword: String) {

        val validationResult = validationAuth.validateFieldRegistrations(firstName, lastName, email, password, confirmPassword)
        if (!validationResult.isValid) {
            _validationResult.value = validationResult
            return
        }

        // Registration logic would go here, similar to the login function in LoginViewModel
    }

    fun clearValidationResults() {
        _validationResult.value = ValidationResult()
    }
}