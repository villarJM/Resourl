package com.devvillar.resourl.features.auth.data.datasources.remote.dto

import com.google.gson.annotations.SerializedName

data class UserSessionDto(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("expired_date") val expiredDate: Long,
)