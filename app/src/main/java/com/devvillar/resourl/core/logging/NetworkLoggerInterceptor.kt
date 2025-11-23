package com.devvillar.resourl.core.logging

import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import timber.log.Timber
import javax.inject.Inject

class NetworkLoggerInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // ---- REQUEST ----
        val requestBody = request.body
        val requestBodyString = requestBody?.let {
            val buffer = Buffer()
            it.writeTo(buffer)
            buffer.readUtf8()
        } ?: "No body"

        Timber.tag("API-REQUEST").d(
            """
            ➜ REQUEST
            URL: ${request.url}
            METHOD: ${request.method}
            HEADERS: ${request.headers}
            BODY: $requestBodyString
            """.trimIndent()
        )

        val response = chain.proceed(request)

        // ---- RESPONSE ----
        val responseBodyString = response.body?.let { body ->
            val source = body.source()
            source.request(Long.MAX_VALUE)
            val buffer = source.buffer.clone()
            buffer.readUtf8()
        } ?: "No body"

        Timber.tag("API-RESPONSE").d(
            """
            ✓ RESPONSE
            URL: ${response.request.url}
            CODE: ${response.code}
            MESSAGE: ${response.message}
            HEADERS: ${response.headers}
            BODY: $responseBodyString
            """.trimIndent()
        )

        return response
    }
}
