package com.devvillar.resourl.features.auth.domain.usecases

import com.devvillar.resourl.core.network.ApiResponse
import com.devvillar.resourl.features.auth.data.datasources.remote.request.RegisterRequest
import com.devvillar.resourl.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(request: RegisterRequest): Result<ApiResponse<Nothing>> {
        return repository.register(request)
    }
}