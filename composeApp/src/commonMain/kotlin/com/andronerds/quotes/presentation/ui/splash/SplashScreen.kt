package com.andronerds.quotes.presentation.ui.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.andronerds.quotes.presentation.ui.quote.QuoteScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import quotes.composeapp.generated.resources.Res
import quotes.composeapp.generated.resources.*

class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        // Animation states
        val logoScale = remember { Animatable(0f) }
        val logoRotation = remember { Animatable(0f) }
        val contentAlpha = remember { Animatable(0f) }
        val contentOffsetY = remember { Animatable(50f) }
        val loadingAlpha = remember { Animatable(0f) }

        LaunchedEffect(Unit) {
            // Logo scale animation with bounce
            logoScale.animateTo(
                targetValue = 1f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )

            // Subtle rotation animation
            launch {
                logoRotation.animateTo(
                    targetValue = 360f,
                    animationSpec = tween(2000, easing = FastOutSlowInEasing)
                )
            }

            // Content fade in
            delay(300)
            contentAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(800, easing = LinearOutSlowInEasing)
            )
            contentOffsetY.animateTo(
                targetValue = 0f,
                animationSpec = tween(800, easing = LinearOutSlowInEasing)
            )

            // Loading indicator fade in
            delay(200)
            loadingAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(600)
            )

            // Navigate to main screen
            delay(2500)
            navigator.replace(QuoteScreen())
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                        )
                    )
                )
        ) {
            // Decorative circles in background
            DecorativeCircles()

            // Main content
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.weight(1f))

                // Logo Section
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(120.dp)
                        .scale(logoScale.value)
                        .rotate(logoRotation.value)
                        .shadow(
                            elevation = 20.dp,
                            shape = RoundedCornerShape(24.dp),
                            ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                            spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                        )
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.primaryContainer
                                )
                            ),
                            shape = RoundedCornerShape(24.dp)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Filled.FormatQuote,
                        contentDescription = "App Logo",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(56.dp)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // App Name with animation
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .alpha(contentAlpha.value)
                        .offset(y = contentOffsetY.value.dp)
                ) {
                    Text(
                        text = stringResource(Res.string.quote_vault),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.displaySmall.copy(
                            letterSpacing = 1.sp
                        ),
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = stringResource(Res.string.inspiration_at_fingertips),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // Loading Section
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.alpha(loadingAlpha.value)
                ) {
                    // Animated loading dots
                    LoadingDotsAnimation()

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(Res.string.loading_dots),
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))

                // Version text
                Text(
                    text = stringResource(Res.string.version),
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                    style = MaterialTheme.typography.labelSmall,
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

    @Composable
    private fun DecorativeCircles() {
        val infiniteTransition = rememberInfiniteTransition(label = "decorative")

        // Top right circle
        val topRightScale by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.2f,
            animationSpec = infiniteRepeatable(
                animation = tween(3000, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "topRight"
        )

        // Bottom left circle
        val bottomLeftAlpha by infiniteTransition.animateFloat(
            initialValue = 0.1f,
            targetValue = 0.2f,
            animationSpec = infiniteRepeatable(
                animation = tween(4000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "bottomLeft"
        )

        Box(modifier = Modifier.fillMaxSize()) {
            // Top right decorative circle
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .offset(x = 100.dp, y = (-50).dp)
                    .scale(topRightScale)
                    .align(Alignment.TopEnd)
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
                        shape = CircleShape
                    )
            )

            // Middle left circle
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .offset(x = (-50).dp, y = 200.dp)
                    .align(Alignment.CenterStart)
                    .background(
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.06f),
                        shape = CircleShape
                    )
            )

            // Bottom right circle
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .offset(x = 50.dp, y = 100.dp)
                    .align(Alignment.BottomEnd)
                    .background(
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = bottomLeftAlpha),
                        shape = CircleShape
                    )
            )
        }
    }

    @Composable
    private fun LoadingDotsAnimation() {
        val infiniteTransition = rememberInfiniteTransition(label = "loading")

        val delays = listOf(0, 150, 300)

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            delays.forEach { delay ->
                val scale by infiniteTransition.animateFloat(
                    initialValue = 0.6f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(600, delayMillis = delay, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "dot$delay"
                )

                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .scale(scale)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                )
            }
        }
    }
}
