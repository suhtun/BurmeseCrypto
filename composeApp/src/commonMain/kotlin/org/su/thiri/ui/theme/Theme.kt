package org.su.thiri.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = darkThemePrimaryColor,
    background = darkThemeBgColor,
    onPrimary = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = lightThemePrimaryColor,
    background = Color.White,
    onPrimary = Color.White,
)

@Composable
fun BurmeseCryptoTheme(
    content: @Composable () -> Unit
) {

    val colors by mutableStateOf(
        if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
    )

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}