package com.devvillar.resourl.features.auth.data.datasources.remote

import com.devvillar.resourl.core.network.ApiResponse
import com.devvillar.resourl.core.network.NetworkClient
import com.devvillar.resourl.core.utils.RetrofitClientUtil
import com.devvillar.resourl.features.auth.data.apiclients.AuthApiClient
import com.devvillar.resourl.features.auth.data.datasources.remote.request.LoginRequest
import com.devvillar.resourl.features.auth.data.datasources.remote.dto.UserSessionDto
import com.devvillar.resourl.features.auth.data.datasources.remote.request.AccountVerificationRequest
import com.devvillar.resourl.features.auth.data.datasources.remote.request.RegisterRequest
import timber.log.Timber
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val networkClient: NetworkClient,
    private val retrofitClientUtil: RetrofitClientUtil,
) {

    companion object {
        private const val TAG = "AuthRemoteDataSource"
    }

    private val apiService: AuthApiClient by lazy {
        retrofitClientUtil.createService(AuthApiClient::class.java)
    }


    suspend fun login(request: LoginRequest): Result<ApiResponse<UserSessionDto>> {
        Timber.tag(TAG).d("login: $request")
        return networkClient.safeApiCall {
            apiService.login(request)
        }
    }

    suspend fun register(request: RegisterRequest): Result<ApiResponse<Nothing>> {
        Timber.tag(TAG).d("register: $request")
        return networkClient.safeApiCall {
            apiService.register(request)
        }
    }

    suspend fun forgotPassword(email: String): Result<ApiResponse<Nothing>> {
        Timber.tag(TAG).d("forgotPassword: $email")
        return networkClient.safeApiCall {
            apiService.forgotPassword(email)
        }
    }

    suspend fun accountVerification(request: AccountVerificationRequest): Result<ApiResponse<Nothing>> {
        Timber.tag(TAG).d("accountVerification: $request")
        return networkClient.safeApiCall {
            apiService.accountVerification(request)
        }
    }
}