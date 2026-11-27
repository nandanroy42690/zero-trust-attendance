
package com.zerotrust.attendance.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = androidx.compose.ui.graphics.Color.White,

    secondary = SuccessGreen,
    onSecondary = androidx.compose.ui.graphics.Color.White,

    background = LightBackground,
    onBackground = LightText,

    surface = LightSurface,
    onSurface = LightText,

    surfaceVariant = LightBorder,
    onSurfaceVariant = LightSecondaryText,

    error = ErrorRed
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = DarkBackground,

    secondary = SuccessGreen,
    onSecondary = DarkBackground,

    background = DarkBackground,
    onBackground = DarkText,

    surface = DarkSurface,
    onSurface = DarkText,

    surfaceVariant = DarkBorder,
    onSurfaceVariant = DarkSecondaryText,

    error = ErrorRed
)

@Composable
fun ZeroTrustAttendanceTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
