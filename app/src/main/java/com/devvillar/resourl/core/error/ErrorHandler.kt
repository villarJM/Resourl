package com.devvillar.resourl.core.error

import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorHandler @Inject constructor() {

    companion object {
        private val gson = Gson()
    }

    fun getErrorMessage(throwable: Throwable): String {
        return when (throwable) {
            is UnknownHostException -> "No internet connection"
            is ConnectException -> "Failed to connect to the server"
            is SocketTimeoutException -> "The connection has timed out"
            is HttpException -> {
                val errorBody = throwable.response()?.errorBody()?.string()
                val parsedMessage = parseErrorMessage(errorBody)
                when (throwable.code()) {
                    400 -> parsedMessage ?: "Bad Request"
                    401 -> parsedMessage ?: "Unauthorized. Please log in again."
                    403 -> parsedMessage ?: "Forbidden"
                    404 -> parsedMessage ?: "Resource not found"
                    422 -> parsedMessage ?: "Unprocessable Entity"
                    429 -> parsedMessage ?: "Too Many Requests"
                    500 -> parsedMessage ?: "Internal server error"
                    502 -> "Bad Gateway"
                    503 -> "Service Unavailable"
                    504 -> "Gateway Timeout"
                    else -> parsedMessage ?: "HTTP error ${throwable.code()} - ${throwable.message()}"
                }
            }
            else -> throwable.message ?: "An unexpected error occurred"

        }
    }

    private fun parseErrorMessage(errorBody: String?): String? {
        return try {
            if (errorBody.isNullOrEmpty()) return null
            val errorResponse = gson.fromJson(errorBody, JsonObject::class.java)
            errorResponse["message"].toString()
        } catch (e: Exception) {
            null
        }
    }
}