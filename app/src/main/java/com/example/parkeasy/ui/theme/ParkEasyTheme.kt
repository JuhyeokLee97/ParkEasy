package com.example.parkeasy.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.parkeasy.ui.theme.color.DarkExtendedColors
import com.example.parkeasy.ui.theme.color.LightExtendedColors
import com.example.parkeasy.ui.theme.color.LocalExtendedColors
import com.example.parkeasy.ui.theme.color.ParkEasyDarkColorScheme
import com.example.parkeasy.ui.theme.color.ParkEasyLightColorScheme


@Composable
fun ParkEasyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        ParkEasyDarkColorScheme
    } else {
        ParkEasyLightColorScheme
    }
    val extendedColor = if (darkTheme) {
        DarkExtendedColors
    } else {
        LightExtendedColors
    }
    CompositionLocalProvider(
        LocalExtendedColors provides extendedColor
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }

}