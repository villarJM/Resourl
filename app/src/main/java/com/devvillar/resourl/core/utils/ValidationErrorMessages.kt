package com.devvillar.resourl.core.utils

import android.content.Context
import com.devvillar.resourl.R
import com.devvillar.resourl.core.utils.ValidationUtils.Companion.MIN_PASSWORD_LENGTH
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidationErrorMessages @Inject constructor(
    @param:ApplicationContext private val context: Context
) {

    /*
    *  Validation error messages retrieved from string resources
    *  This class centralizes error message management for validation
    *  improving maintainability and localization support
    *  Each message corresponds to a specific validation error
    *  */

    val emailEmptyError: String = context.getString(R.string.login_email_empty_error)
    val emailInvalidError: String = context.getString(R.string.login_email_invalid_error)
    val passwordEmptyError: String = context.getString(R.string.login_password_empty_error)
    val passwordInvalidError: String = context.getString(R.string.login_password_invalid_error, MIN_PASSWORD_LENGTH)
    val confirmPasswordEmptyError: String = context.getString(R.string.register_confirm_password_empty_error)
    val confirmPasswordMismatchError: String = context.getString(R.string.register_confirm_password_mismatch_error)
    val firstNameEmptyError: String = context.getString(R.string.register_name_empty_error)
    val firstNameWhitespaceError: String = context.getString(R.string.register_name_whitespace_error)
    val lastNameEmptyError: String = context.getString(R.string.register_last_name_empty_error)
    val lastNameWhitespaceError: String = context.getString(R.string.register_last_name_whitespace_error)

}