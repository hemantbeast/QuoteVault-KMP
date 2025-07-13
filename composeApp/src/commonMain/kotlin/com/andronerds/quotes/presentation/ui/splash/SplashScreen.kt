package com.andronerds.quotes.presentation.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.andronerds.quotes.presentation.ui.quote.QuoteScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import quotes.composeapp.generated.resources.Res
import quotes.composeapp.generated.resources.quote_vault

class SplashScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val coroutineScope = rememberCoroutineScope()

        val fadeAnimation = remember { Animatable(0f) }
        val scaleAnimation = remember { Animatable(0.6f) }

        LaunchedEffect(Unit) {
            fadeAnimation.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing)
            )
        }

        LaunchedEffect(Unit) {
            scaleAnimation.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 2000, easing = LinearOutSlowInEasing)
            )
        }

        LaunchedEffect(Unit) {
            coroutineScope.launch {
                delay(2500)
                navigator.replace(QuoteScreen())
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(3f)
                        .fillMaxSize(),
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .alpha(fadeAnimation.value)
                            .scale(scaleAnimation.value)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(100.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(8.dp),
                                )
                                .shadow(
                                    elevation = 10.dp,
                                    ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                                    spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                                )
                        ) {
                            Icon(
                                imageVector = Icons.Filled.FormatQuote,
                                contentDescription = "App Logo",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = stringResource(Res.string.quote_vault),
                            fontWeight = FontWeight.W600,
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.headlineLarge,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Inspiration at your fingertips",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp),
                            strokeWidth = 3.dp,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Initializing...",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }
    }
}