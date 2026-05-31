package com.dreamrecall.app.core.design

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Exclusive Dark-Only Dream Theme Color Scheme
private val DreamDarkColorScheme = darkColorScheme(
    primary = DreamPurple,
    onPrimary = SpaceNavyDark,
    primaryContainer = DreamVioletDark,
    onPrimaryContainer = DreamLavender,
    secondary = DreamLavender,
    onSecondary = SpaceNavyDark,
    tertiary = LucidCyan,
    onTertiary = SpaceNavyDark,
    background = SpaceNavyDark,
    onBackground = TextPrimary,
    surface = SpaceNavyCard,
    onSurface = TextPrimary,
    surfaceVariant = SpaceNavyBorder,
    onSurfaceVariant = TextSecondary,
    outline = SpaceNavyBorder,
    outlineVariant = SpaceNavyBorderGlow,
    error = NightmarePink,
    onError = SpaceNavyDark
)

@Composable
fun DreamRecallTheme(
    // Forced dark-only mode to preserve the mystical dream atmosphere
    darkTheme: Boolean = true, 
    content: @Composable () -> Unit
) {
    val colorScheme = DreamDarkColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                // Set status bar and navigation bar color matching deep navy
                window.statusBarColor = SpaceNavyDark.toArgb()
                window.navigationBarColor = SpaceNavyDark.toArgb()

                val insetsController = WindowCompat.getInsetsController(window, view)
                // Set dark theme appearance (white status bar icons)
                insetsController.isAppearanceLightStatusBars = false
                insetsController.isAppearanceLightNavigationBars = false
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = DreamTypography,
        shapes = DreamShapes,
        content = content
    )
}
