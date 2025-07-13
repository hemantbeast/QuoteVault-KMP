package com.andronerds.quotes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.andronerds.quotes.presentation.theme.AppTheme
import com.andronerds.quotes.presentation.ui.splash.SplashScreen
import com.andronerds.quotes.utils.extensions.clearFocusOnTapOutside
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize()
                .clearFocusOnTapOutside()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.05f),
                        )
                    )
                )
        ) {
            Navigator(SplashScreen()) { navigator ->
                SlideTransition(navigator)
            }
        }
    }
}