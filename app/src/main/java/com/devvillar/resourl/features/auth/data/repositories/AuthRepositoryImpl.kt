package com.devvillar.resourl.features.auth.data.repositories

import com.devvillar.resourl.core.network.ApiResponse
import com.devvillar.resourl.core.utils.PrefsManager
import com.devvillar.resourl.features.auth.data.datasources.remote.AuthRemoteDataSource
import com.devvillar.resourl.features.auth.data.datasources.remote.mappers.toDomain
import com.devvillar.resourl.features.auth.data.datasources.remote.request.AccountVerificationRequest
import com.devvillar.resourl.features.auth.data.datasources.remote.request.LoginRequest
import com.devvillar.resourl.features.auth.data.datasources.remote.request.RegisterRequest
import com.devvillar.resourl.features.auth.domain.models.UserSession
import com.devvillar.resourl.features.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val prefsManager: PrefsManager,
    private val remoteDataSource: AuthRemoteDataSource,
): AuthRepository {

    override suspend fun login(request: LoginRequest): Result<UserSession> {
        return remoteDataSource.login(request)
            .mapCatching { apiResponse ->
                val session = apiResponse.data?.toDomain()
                    ?: throw Exception("No user session data")
                prefsManager.saveTokens(session.accessToken, session.refreshToken)
                session

        }
    }

    override suspend fun register(request: RegisterRequest): Result<ApiResponse<Nothing>> {
        return remoteDataSource.register(request)
    }

    override suspend fun forgotPassword(email: String): Result<ApiResponse<Nothing>> {
        return remoteDataSource.forgotPassword(email)
    }

    override suspend fun accountVerification(request: AccountVerificationRequest): Result<ApiResponse<Nothing>> {
        return remoteDataSource.accountVerification(request)
    }

}