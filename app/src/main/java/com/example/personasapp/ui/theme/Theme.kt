package com.example.personasapp.ui.theme

import android.app.Activity
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
    primary = Lavanda,
    secondary = LilaPrincipal,
    tertiary = Lavanda,
    primaryContainer = LilaOscuro,
    onPrimary = LilaOscuro,
    onPrimaryContainer = LavandaClaro,
    surface = Color(0xFF1D1A20),
    background = Color(0xFF1D1A20),
    onSurface = Color.White,
    onSurfaceVariant = Color(0xFFD0C8D5)
)

private val LightColorScheme = lightColorScheme(

    primary = LilaPrincipal,

    onPrimary = Color.White,

    primaryContainer = Lavanda,

    onPrimaryContainer = LilaOscuro,

    secondary = LilaOscuro,

    background = LavandaClaro,

    surface = Color.White,

    onSurface = TextoOscuro,

    onSurfaceVariant = TextoSecundario

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun PersonasAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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