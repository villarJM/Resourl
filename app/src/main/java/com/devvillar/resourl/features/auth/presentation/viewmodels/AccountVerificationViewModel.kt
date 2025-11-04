package com.devvillar.resourl.features.auth.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.devvillar.resourl.core.base.BaseViewModel
import com.devvillar.resourl.core.network.ApiResponse
import com.devvillar.resourl.core.state.UIState
import com.devvillar.resourl.core.utils.ValidationAuth
import com.devvillar.resourl.core.utils.ValidationResult
import com.devvillar.resourl.features.auth.data.datasources.remote.request.AccountVerificationRequest
import com.devvillar.resourl.features.auth.domain.usecases.AccountVerificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountVerificationViewModel @Inject constructor(
    private val accountVerificationUseCase: AccountVerificationUseCase,
    private val validationAuth: ValidationAuth
): BaseViewModel() {

    private val _accountVerificationUIState = MutableStateFlow<UIState<ApiResponse<Nothing>>>(UIState.Initial)
    val accountVerificationUIState: StateFlow<UIState<ApiResponse<Nothing>>> = _accountVerificationUIState.asStateFlow()

    private val _validationResult = MutableStateFlow(ValidationResult())
    val validationResult: StateFlow<ValidationResult> = _validationResult.asStateFlow()

    private val _otpCode = MutableStateFlow("")
    val otpCode: StateFlow<String> = _otpCode.asStateFlow()

    val isFormValid: StateFlow<Boolean> = _otpCode.map { code ->
        validationAuth.validateFieldAccountVerification(code).isValid
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun onOtpCodeChanged(code: String) {
        _otpCode.value = code
    }


    fun accountVerification() {
        val validationResult = validationAuth.validateFieldAccountVerification(_otpCode.value)

        if (_validationResult.value.isValid) {
            _validationResult.value = validationResult
            return
        }

        val request = AccountVerificationRequest(
            email = "",
            otpCode = _otpCode.value
        )


        viewModelScope.launch(Dispatchers.IO) {

            _accountVerificationUIState.value = UIState.Loading

            val result = accountVerificationUseCase(request)
            result.fold(
                onSuccess = { apiResponse ->
                    _accountVerificationUIState.value = UIState.Success(apiResponse)
                },
                onFailure = { error ->
                    _accountVerificationUIState.value = UIState.Error(error.message.orEmpty())
                }
            )
        }
    }


}