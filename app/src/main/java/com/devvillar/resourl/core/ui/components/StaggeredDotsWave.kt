package com.devvillar.resourl.core.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StaggeredDotsWave(
    modifier: Modifier = Modifier,
    dotColor: Color = MaterialTheme.colorScheme.primary,
    dotSize: Dp = 8.dp,
    travelDistance: Dp = 8.dp,
    delayBetweenDots: Int = 150,
    dotCount: Int = 3
) {
    val infiniteTransition = rememberInfiniteTransition(label = "dots_wave")

    // Creamos una animación independiente por punto
    val animations = List(dotCount) { index ->
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = dotCount * delayBetweenDots
                    0f at 0
                    1f at delayBetweenDots / 2
                    0f at delayBetweenDots
                },
                repeatMode = RepeatMode.Restart,
                initialStartOffset = StartOffset(index * delayBetweenDots)
            ),
            label = "dot_$index"
        )
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        animations.forEach { anim ->
            val offsetY = -travelDistance * anim.value
            Box(
                modifier = Modifier
                    .size(dotSize)
                    .offset(y = offsetY)
                    .background(dotColor, shape = CircleShape)
            )
        }
    }
}
