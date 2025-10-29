package com.devvillar.resourl.features.auth.domain.repositories

import com.devvillar.resourl.features.auth.domain.models.UserSession

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<UserSession>
}