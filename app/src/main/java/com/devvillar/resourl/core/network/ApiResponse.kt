package com.devvillar.resourl.core.network

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: T?,
    @SerializedName("status_code") val statusCode: Int
)
