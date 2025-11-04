package com.devvillar.resourl.features.auth.domain.usecases

import com.devvillar.resourl.core.network.ApiResponse
import com.devvillar.resourl.features.auth.data.datasources.remote.request.AccountVerificationRequest
import com.devvillar.resourl.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class AccountVerificationUseCase @Inject constructor(
    private val authRepository: AuthRepository,
)  {
    suspend operator fun invoke(request: AccountVerificationRequest): Result<ApiResponse<Nothing>> {
        return authRepository.accountVerification(request)
    }
}