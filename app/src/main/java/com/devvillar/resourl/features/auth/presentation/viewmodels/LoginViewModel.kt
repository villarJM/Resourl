package com.devvillar.resourl.features.auth.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.devvillar.resourl.core.base.BaseViewModel
import com.devvillar.resourl.core.state.UIState
import com.devvillar.resourl.core.utils.PrefsManager
import com.devvillar.resourl.core.utils.ValidationAuth
import com.devvillar.resourl.core.utils.ValidationResult
import com.devvillar.resourl.features.auth.data.datasources.remote.request.LoginRequest
import com.devvillar.resourl.features.auth.domain.models.UserSession
import com.devvillar.resourl.features.auth.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val prefsManager: PrefsManager,
    private val validationAuth: ValidationAuth
) : BaseViewModel() {

    private val _loginUIState = MutableStateFlow<UIState<UserSession>>(UIState.Initial)
    val loginUIState: StateFlow<UIState<UserSession>> = _loginUIState.asStateFlow()

    private val _validationResult = MutableStateFlow(ValidationResult())
    val validationResult: StateFlow<ValidationResult> = _validationResult.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()


    val isFormValid: StateFlow<Boolean> = combine(_email, _password) { email, password ->
        validationAuth.validateFieldLogins(email, password).isValid
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun onEmailChange(newEmail: String) {
        clearValidationResults()
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        clearValidationResults()
        _password.value = newPassword
    }

    fun login() {

        val validationResult = validationAuth.validateFieldLogins(_email.value, _password.value)
        if (!validationResult.isValid) {
            _validationResult.value = validationResult
            return
        }

        val loginRequest = LoginRequest(
            email = _email.value,
            password = _password.value
        )

        viewModelScope.launch {

            _loginUIState.value = UIState.Loading

            val result = loginUseCase(loginRequest)
            result.fold(
                onSuccess = { userSession ->
                    prefsManager.saveTokens(userSession.accessToken, userSession.refreshToken)
                    _loginUIState.value = UIState.Success(userSession)
                },
                onFailure = { error ->
                    _loginUIState.value = UIState.Error(error.message.orEmpty())
                }
            )
        }
    }

    fun clearValidationResults() {
        _validationResult.value = ValidationResult()
    }

}