package com.devvillar.resourl.core.network.interceptors

import android.content.Context
import com.devvillar.resourl.core.utils.LocaleUtils
import com.devvillar.resourl.core.utils.PrefsManager
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val prefsManager: PrefsManager,
    @param:ApplicationContext private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val langCode = LocaleUtils.getLanguageCode(context)
        val accessToken = prefsManager.getAccessToken()
        val originalRequest = chain.request()

        val requestBuilder = originalRequest.newBuilder()
            .header("Accept", "application/json")
            .header("Accept-Language", langCode)

        if (originalRequest.body != null) {
            requestBuilder.header("Content-Type", "application/json")
        }

        if (!accessToken.isNullOrEmpty()) {
            requestBuilder.header("Authorization", "Bearer $accessToken")
        }

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}