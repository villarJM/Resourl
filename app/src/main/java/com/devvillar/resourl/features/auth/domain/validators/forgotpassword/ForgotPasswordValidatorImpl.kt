package com.devvillar.resourl.features.auth.domain.validators.forgotpassword

import android.provider.SyncStateContract.Helpers.update
import com.devvillar.resourl.core.validator.EmailValidator
import com.devvillar.resourl.core.validator.PasswordValidator
import com.devvillar.resourl.core.validator.base.FieldValidationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ForgotPasswordValidatorImpl(
    private val emailValidator: EmailValidator,
): ForgotPasswordValidator {

    private val _state = MutableStateFlow(ForgotPasswordValidationState())
    override val state: StateFlow<ForgotPasswordValidationState> = _state

    override fun onEmailChanged(value: String) {
        update(email = emailValidator.validate(value))
    }

    private fun update(
        email: FieldValidationState = _state.value.email
    ) {
        val isFormValid = listOf(email.isValid).all { it }
        _state.value = ForgotPasswordValidationState(email, isFormValid)

    }

}