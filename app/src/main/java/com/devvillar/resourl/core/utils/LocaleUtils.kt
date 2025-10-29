package com.devvillar.resourl.core.utils

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import java.util.Locale

object LocaleUtils {

    fun getLanguageCode(context: Context): String {
        val locale: Locale? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val localeManager = context.getSystemService(LocaleManager::class.java)
            localeManager?.applicationLocales?.takeIf { !it.isEmpty }?.get(0)
                ?: localeManager?.systemLocales?.get(0)
        } else {
            @Suppress("DEPRECATION")
            context.resources.configuration.locales.get(0)
        }
        return locale?.language ?: Locale.getDefault().language
    }
}