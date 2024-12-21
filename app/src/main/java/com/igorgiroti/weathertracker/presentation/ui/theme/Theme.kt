package com.igorgiroti.weathertracker.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val colorScheme = lightColorScheme(
    primary = Color.Black,
    secondary = LightGray,
    tertiary = Color.Black,
    background = Color.White
)

@Composable
fun WeatherTrackerTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = com.igorgiroti.weathertracker.presentation.ui.theme.colorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}