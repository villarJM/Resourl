package com.devvillar.resourl.features.auth.data.datasources.remote.request

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)