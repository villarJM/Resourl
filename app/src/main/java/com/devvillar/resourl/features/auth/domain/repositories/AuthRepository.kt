package com.devvillar.resourl.features.auth.domain.repositories

import com.devvillar.resourl.core.network.ApiResponse
import com.devvillar.resourl.features.auth.data.datasources.remote.request.LoginRequest
import com.devvillar.resourl.features.auth.data.datasources.remote.request.RegisterRequest
import com.devvillar.resourl.features.auth.domain.models.UserSession

interface AuthRepository {
    suspend fun login(request: LoginRequest): Result<UserSession>
    suspend fun register(request: RegisterRequest): Result<ApiResponse<Nothing>>
    suspend fun forgotPassword(email: String): Result<ApiResponse<Nothing>>
}