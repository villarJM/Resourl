package com.devvillar.resourl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.devvillar.resourl.core.ui.navegation.ResourlNavigation
import com.devvillar.resourl.core.ui.theme.ResourlTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResourlTheme(
                darkTheme = false
            ) {
                ResourlNavigation()
            }
        }
    }
}