package com.devvillar.resourl

import android.app.Application
import com.devvillar.resourl.core.logging.DebugTreeWithLine
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ResourlApp: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTreeWithLine())
        }
    }

}