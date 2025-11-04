package com.devvillar.resourl.features.auth.data.datasources.remote.request

data class AccountVerificationRequest(
    val email: String,
    val otpCode: String,
)