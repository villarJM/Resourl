package com.devvillar.resourl.features.auth.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.devvillar.resourl.core.base.BaseViewModel
import com.devvillar.resourl.core.state.UIState
import com.devvillar.resourl.core.utils.PrefsManager
import com.devvillar.resourl.core.utils.ValidationResult
import com.devvillar.resourl.core.utils.ValidationUtils
import com.devvillar.resourl.features.auth.domain.models.UserSession
import com.devvillar.resourl.features.auth.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val prefsManager: PrefsManager,
    private val validationUtils: ValidationUtils
) : BaseViewModel() {

    private val _loginUIState = MutableStateFlow<UIState<UserSession>>(UIState.Initial)
    val loginUIState: StateFlow<UIState<UserSession>> = _loginUIState.asStateFlow()

    private val _validationResult = MutableStateFlow(ValidationResult())
    val validationResult: StateFlow<ValidationResult> = _validationResult.asStateFlow()

    fun login(email: String, password: String) {

        val validationResult = validationUtils.validateFieldLogins(email, password)
        if (!validationResult.isValid) {
            _validationResult.value = validationResult
            return
        }

        viewModelScope.launch {

            _loginUIState.value = UIState.Loading

            val result = loginUseCase(email, password)
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