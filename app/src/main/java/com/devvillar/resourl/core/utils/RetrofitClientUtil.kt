package com.devvillar.resourl.core.utils

import com.devvillar.resourl.core.logging.NetworkLoggerInterceptor
import com.devvillar.resourl.core.network.interceptors.AuthInterceptor
import com.devvillar.resourl.core.network.interceptors.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Utilidad para crear servicios Retrofit por feature.
 *
 * Dos modos:
 * - createServiceWithAuth: Usa configuración completa con AuthInterceptor
 * - createService: Sin autenticación (para endpoints públicos o APIs externas)
 */
@Singleton
class RetrofitClientUtil @Inject constructor(
    private val authInterceptor: AuthInterceptor,
    private val loggingInterceptor: NetworkLoggerInterceptor,
    private val networkConnectionInterceptor: NetworkConnectionInterceptor
) {

    /**
     * Crea un servicio API CON autenticación.
     * Usa todos los interceptores: Auth, Logging, NetworkConnection.
     */
    fun <T> createServiceWithAuth(
        serviceClass: Class<T>,
        url: String? = null
    ): T {
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .addInterceptor(networkConnectionInterceptor)
            .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(url ?: Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(serviceClass)
    }

    /**
     * Versión inline con auth.
     */
    inline fun <reified T> createServiceWithAuth(url: String? = null): T =
        createServiceWithAuth(T::class.java, url)

    /**
     * Crea un servicio API SIN autenticación.
     * Solo usa Logging y NetworkConnection (sin AuthInterceptor).
     */
    fun <T> createService(
        serviceClass: Class<T>,
        url: String? = null
    ): T {
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(networkConnectionInterceptor)
            .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(url ?: Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(serviceClass)
    }

    /**
     * Versión inline sin auth.
     */
    inline fun <reified T> createService(url: String? = null): T =
        createService(T::class.java, url)
}