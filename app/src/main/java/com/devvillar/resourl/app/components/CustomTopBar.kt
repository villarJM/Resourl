package com.devvillar.resourl.app.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devvillar.resourl.features.addresource.presentation.events.AddResourceEvent

@Composable
fun CustomTopBar(
    title: String,
    onEventLeft: (() -> Unit)? = null,
    onEventRight: (() -> Unit)? = null,
    iconLeft: (@Composable () -> Unit)? = null,
    iconRight: (@Composable () -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        if (onEventLeft != null && iconLeft != null) {
            IconButton(
                onClick = onEventLeft,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                iconLeft()
            }
        }

        Text(
            text = title,
            modifier = Modifier.align(Alignment.Center)
        )

        if (onEventRight != null && iconRight != null) {
            IconButton(
                onClick = onEventRight,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                iconRight()
            }
        }
    }
}