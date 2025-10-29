package com.devvillar.resourl.features.auth.data.datasources.remote

import com.devvillar.resourl.core.network.ApiResponse
import com.devvillar.resourl.core.network.ApiService
import com.devvillar.resourl.core.network.NetworkClient
import com.devvillar.resourl.features.auth.data.datasources.remote.request.LoginRequest
import com.devvillar.resourl.features.auth.data.datasources.remote.dto.UserSessionDto
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val networkClient: NetworkClient
) {

    suspend fun login(email: String, password: String): Result<ApiResponse<UserSessionDto>> {
        val request = LoginRequest(email, password)
        return networkClient.safeApiCall {
            apiService.login(request)
        }
    }
}