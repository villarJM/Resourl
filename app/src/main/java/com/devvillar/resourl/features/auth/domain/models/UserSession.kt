package com.devvillar.resourl.features.auth.domain.models

data class UserSession(
    val accessToken: String,
    val refreshToken: String,
    val expiredDate: Long,
    ) {
    override fun toString(): String = "UserSession(accessToken=***** refreshToken=*****, expiredDate=$expiredDate)"
}
