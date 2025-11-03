package com.devvillar.resourl.features.auth.domain.usecases

import com.devvillar.resourl.core.network.ApiResponse
import com.devvillar.resourl.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject
class ForgotPasswordUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String): Result<ApiResponse<Nothing>> {
        return repository.forgotPassword(email)
    }
}