package com.devvillar.resourl.features.auth.domain.usecases

import com.devvillar.resourl.features.auth.domain.models.UserSession
import com.devvillar.resourl.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Result<UserSession> {
        return repository.login(email, password)
    }
}