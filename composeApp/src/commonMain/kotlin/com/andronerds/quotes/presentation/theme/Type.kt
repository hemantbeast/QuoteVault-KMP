package com.andronerds.quotes.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import quotes.composeapp.generated.resources.*

val OpenSans: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.open_sans_regular, FontWeight.Normal),
        Font(Res.font.open_sans_bold, FontWeight.Bold),
        Font(Res.font.open_sans_light, FontWeight.Light),
        Font(Res.font.open_sans_semibold, FontWeight.SemiBold),
        Font(Res.font.open_sans_medium, FontWeight.Medium),
    )

val AppTypography
    @Composable
    get() = Typography().let {
        it.copy(
            displayLarge = it.displayLarge.copy(fontWeight = FontWeight.ExtraBold, fontFamily = OpenSans),
            displayMedium = it.displayMedium.copy(fontWeight = FontWeight.ExtraBold, fontFamily = OpenSans),
            displaySmall = it.displaySmall.copy(fontWeight = FontWeight.Bold, fontFamily = OpenSans),
            titleLarge = it.titleLarge.copy(fontWeight = FontWeight.Bold, fontFamily = OpenSans),
            titleMedium = it.titleMedium.copy(fontWeight = FontWeight.Bold, fontFamily = OpenSans),
            headlineLarge = it.headlineLarge.copy(fontWeight = FontWeight.SemiBold, fontFamily = OpenSans),
            headlineMedium = it.headlineMedium.copy(fontWeight = FontWeight.SemiBold, fontFamily = OpenSans),
            headlineSmall = it.headlineSmall.copy(fontWeight = FontWeight.SemiBold, fontFamily = OpenSans),
            bodyLarge = it.bodyLarge.copy(fontWeight = FontWeight.Medium, fontFamily = OpenSans),
            bodyMedium = it.bodyMedium.copy(fontWeight = FontWeight.Normal, fontFamily = OpenSans),
            bodySmall = it.bodySmall.copy(fontWeight = FontWeight.Normal, fontFamily = OpenSans),
            labelLarge = it.labelLarge.copy(fontWeight = FontWeight.Normal, fontFamily = OpenSans),
            labelMedium = it.labelMedium.copy(fontWeight = FontWeight.Light, fontFamily = OpenSans),
            labelSmall = it.labelSmall.copy(fontWeight = FontWeight.Light, fontFamily = OpenSans),
        )
    }
