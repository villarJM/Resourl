package com.devvillar.resourl.features.auth.data.apiclients

import com.devvillar.resourl.core.network.ApiResponse
import com.devvillar.resourl.features.auth.data.datasources.remote.dto.UserSessionDto
import com.devvillar.resourl.features.auth.data.datasources.remote.request.AccountVerificationRequest
import com.devvillar.resourl.features.auth.data.datasources.remote.request.LoginRequest
import com.devvillar.resourl.features.auth.data.datasources.remote.request.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiClient {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<UserSessionDto>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): ApiResponse<Nothing>

    @POST("auth/logout")
    suspend fun logout()

    @POST("auth/refresh-token")
    suspend fun refreshToken()

    @POST("auth/forgot-password")
    suspend fun forgotPassword(@Body email: String): ApiResponse<Nothing>

    @POST("auth/reset-password")
    suspend fun resetPassword()

    @POST("auth/account-verification")
    suspend fun accountVerification(@Body request: AccountVerificationRequest): ApiResponse<Nothing>
}