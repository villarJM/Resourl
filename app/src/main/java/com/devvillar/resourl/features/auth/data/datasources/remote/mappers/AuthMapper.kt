package com.devvillar.resourl.features.auth.data.datasources.remote.mappers

import com.devvillar.resourl.features.auth.data.datasources.remote.dto.UserSessionDto
import com.devvillar.resourl.features.auth.domain.models.UserSession

fun UserSessionDto.toDomain(): UserSession {
    return UserSession(
        accessToken = accessToken,
        refreshToken = refreshToken,
        expiredDate = expiredDate,
    )
}
