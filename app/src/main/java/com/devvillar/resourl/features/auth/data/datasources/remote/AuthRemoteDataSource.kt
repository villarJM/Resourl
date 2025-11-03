package com.devvillar.resourl.features.auth.data.datasources.remote

import com.devvillar.resourl.core.network.ApiResponse
import com.devvillar.resourl.core.network.ApiService
import com.devvillar.resourl.core.network.NetworkClient
import com.devvillar.resourl.features.auth.data.datasources.remote.request.LoginRequest
import com.devvillar.resourl.features.auth.data.datasources.remote.dto.UserSessionDto
import com.devvillar.resourl.features.auth.data.datasources.remote.request.RegisterRequest
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val networkClient: NetworkClient
) {

    suspend fun login(request: LoginRequest): Result<ApiResponse<UserSessionDto>> {
        return networkClient.safeApiCall {
            apiService.login(request)
        }
    }

    suspend fun register(request: RegisterRequest): Result<ApiResponse<Nothing>> {
        return networkClient.safeApiCall {
            apiService.register(request)
        }
    }

    suspend fun forgotPassword(email: String): Result<ApiResponse<Nothing>> {
        return networkClient.safeApiCall {
            apiService.forgotPassword(email)
        }
    }
}