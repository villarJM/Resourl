package com.devvillar.resourl.features.auth.domain.validators.login

import com.devvillar.resourl.core.validator.EmailValidator
import com.devvillar.resourl.core.validator.PasswordValidator
import com.devvillar.resourl.core.validator.base.FieldValidationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginValidatorImpl(
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator
): LoginValidator {

    private val _state = MutableStateFlow(LoginValidationState())
    override val state: StateFlow<LoginValidationState> = _state

    override fun onEmailChanged(value: String) {
        update(email = emailValidator.validate(value))
    }

    override fun onPasswordChanged(value: String) {
        update(password = passwordValidator.validate(value))
    }

    private fun update(
        email: FieldValidationState = _state.value.email,
        password: FieldValidationState = _state.value.password
    ) {
        val isFormValid = listOf(email.isValid, password.isValid).all { it }
        _state.value = LoginValidationState(email, password, isFormValid)

    }
}