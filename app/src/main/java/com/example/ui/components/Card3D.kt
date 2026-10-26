package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Card3D(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(18.dp),
    elevation: Dp = 6.dp,
    borderBrush: Brush? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    accentGlowColor: Color? = null,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val clickModifier = if (onClick != null) {
        Modifier.clickable(onClick = onClick)
    } else Modifier

    val defaultBorder = borderBrush ?: Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
            MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
        )
    )

    Surface(
        modifier = modifier
            .shadow(
                elevation = elevation,
                shape = shape,
                ambientColor = accentGlowColor ?: MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                spotColor = accentGlowColor ?: MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            )
            .then(clickModifier),
        shape = shape,
        color = backgroundColor,
        border = BorderStroke(1.2.dp, defaultBorder)
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.04f),
                            Color.Transparent
                        )
                    )
                )
        ) {
            content()
        }
    }
}
