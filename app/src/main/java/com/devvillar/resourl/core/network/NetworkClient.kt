package com.devvillar.resourl.core.network

import com.devvillar.resourl.core.error.ErrorHandler
import javax.inject.Inject

class NetworkClient @Inject constructor(
    private val errorHandler: ErrorHandler
) {
    // A generic function to safely call API endpoints and handle exceptions
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
        return try {
            val result = apiCall()
            Result.success(result)
        } catch (throwable: Throwable) {
            val errorMessage = errorHandler.getErrorMessage(throwable)
            Result.failure(Exception(errorMessage, throwable))
        }
    }
}