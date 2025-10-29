package com.devvillar.resourl.core.network

import com.devvillar.resourl.features.auth.data.datasources.remote.dto.UserSessionDto
import com.devvillar.resourl.features.auth.data.datasources.remote.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<UserSessionDto>

    @POST("auth/register")
    suspend fun register()

    @POST("auth/logout")
    suspend fun logout()

    @POST("auth/refresh-token")
    suspend fun refreshToken()

    @POST("auth/forgot-password")
    suspend fun forgotPassword()

    @POST("auth/reset-password")
    suspend fun resetPassword()
}