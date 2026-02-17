package com.andronerds.quotes.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.materialkolor.DynamicMaterialTheme
import com.materialkolor.PaletteStyle
import com.materialkolor.rememberDynamicMaterialThemeState

@Composable
fun AppTheme(
    seedColor: Color = primaryPurple,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val dynamicThemeState = rememberDynamicMaterialThemeState(
        primary = seedColor,
        isDark = darkTheme,
        style = PaletteStyle.FruitSalad,
    )

    DynamicMaterialTheme(
        state = dynamicThemeState,
        typography = AppTypography,
        content = content,
    )
}
