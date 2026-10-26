package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = ShyamBlueLight,
    onPrimary = ShyamBlueDark,
    primaryContainer = ShyamBluePrimary,
    onPrimaryContainer = Color.White,
    secondary = ShyamGold,
    onSecondary = Color.Black,
    secondaryContainer = ShyamGoldDark,
    onSecondaryContainer = ShyamGoldLight,
    background = BackgroundDark,
    surface = SurfaceDark,
    surfaceVariant = SurfaceDarkCard,
    onBackground = TextPrimaryLight,
    onSurface = TextPrimaryLight,
    outline = SurfaceDarkBorder
)

private val LightColorScheme = lightColorScheme(
    primary = ShyamBluePrimary,
    onPrimary = Color.White,
    primaryContainer = ShyamBlueContainer,
    onPrimaryContainer = ShyamBluePrimary,
    secondary = ShyamGoldDark,
    onSecondary = Color.White,
    secondaryContainer = ShyamGoldLight,
    onSecondaryContainer = Color(0xFF78350F),
    background = BackgroundLight,
    surface = SurfaceLight,
    surfaceVariant = Color(0xFFF1F5F9),
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark,
    outline = SurfaceCardBorder
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Use our branded Royal Navy & Saffron Rajasthan palette
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
