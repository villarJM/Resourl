package com.devvillar.resourl.features.auth.data.repositories

import com.devvillar.resourl.core.network.ApiResponse
import com.devvillar.resourl.features.auth.data.datasources.remote.AuthRemoteDataSource
import com.devvillar.resourl.features.auth.data.datasources.remote.mappers.toDomain
import com.devvillar.resourl.features.auth.data.datasources.remote.request.LoginRequest
import com.devvillar.resourl.features.auth.data.datasources.remote.request.RegisterRequest
import com.devvillar.resourl.features.auth.domain.models.UserSession
import com.devvillar.resourl.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
): AuthRepository {

    override suspend fun login(request: LoginRequest): Result<UserSession> {
        return try {
            val response = remoteDataSource.login(request)
            response.fold(
                onSuccess = { apiResponse ->
                    val userSessionDto = apiResponse.data
                    if (userSessionDto == null) {
                        return Result.failure(Exception("No user session data"))
                    }
                    val userSession = userSessionDto.toDomain()
                    Result.success(userSession)
                },
                onFailure = { error ->
                    Result.failure(error)
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(request: RegisterRequest): Result<ApiResponse<Nothing>> {
        return remoteDataSource.register(request)
    }

    override suspend fun forgotPassword(email: String): Result<ApiResponse<Nothing>> {
        return remoteDataSource.forgotPassword(email)
    }

}