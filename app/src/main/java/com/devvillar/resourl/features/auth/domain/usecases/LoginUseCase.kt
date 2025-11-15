package com.devvillar.resourl.features.auth.domain.usecases

import com.devvillar.resourl.features.auth.data.datasources.remote.request.LoginRequest
import com.devvillar.resourl.features.auth.domain.models.UserSession
import com.devvillar.resourl.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Result<UserSession> {
        val loginRequest = LoginRequest(email, password)
        return repository.login(loginRequest)
    }
}