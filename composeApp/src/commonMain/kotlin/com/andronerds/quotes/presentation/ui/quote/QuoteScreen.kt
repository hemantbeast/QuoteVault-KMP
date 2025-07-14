package com.andronerds.quotes.presentation.ui.quote

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.andronerds.quotes.presentation.components.UiStateHandler
import com.andronerds.quotes.presentation.ui.quote.viewModels.QuoteViewModel
import com.andronerds.quotes.presentation.ui.quote.views.QuoteCard
import com.andronerds.quotes.presentation.ui.quote.views.QuoteTags
import com.dokar.sonner.ToastType
import com.dokar.sonner.Toaster
import com.dokar.sonner.rememberToasterState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import quotes.composeapp.generated.resources.Res
import quotes.composeapp.generated.resources.quote_vault

class QuoteScreen: Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    @Preview
    override fun Content() {
        val hapticFeedback = LocalHapticFeedback.current
        val toaster = rememberToasterState()

        val viewModel = koinScreenModel<QuoteViewModel>()
        val uiState = viewModel.uiState.collectAsState()
        val saved = viewModel.saved.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.getQuote()
        }

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(Res.string.quote_vault),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    },
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Spacer(modifier = Modifier.weight(0.5f))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    QuoteCard(
                        uiState = uiState.value,
                        saved = saved.value,
                        onSaved = {
                            viewModel.saveQuote()
                            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    UiStateHandler(
                        state = uiState.value,
                        loading = {
                            QuoteTags(
                                tags = listOf(),
                                isLoading = true,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    ) { quote ->
                        QuoteTags(
                            tags = quote.tags ?: listOf(),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(0.5f))
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    // Copy Button
                    FilledIconButton(
                        onClick = {
                            viewModel.copyQuote()
                            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)

                            toaster.show(
                                message = "Copied to clipboard.",
                                type = ToastType.Normal,
                            )
                        },
                        modifier = Modifier.size(48.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ContentCopy,
                            contentDescription = "Copy Quote",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    // Share Button
                    FilledIconButton(
                        onClick = {
                            viewModel.shareQuote()
                            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                        },
                        modifier = Modifier.size(48.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Share Quote",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    // Next Button
                    FilledIconButton(
                        onClick = {
                            viewModel.getQuote()
                            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                        },
                        modifier = Modifier.size(48.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                        )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.NavigateNext,
                            contentDescription = "Next Quote",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        Toaster(
            state = toaster,
            alignment = Alignment.TopCenter,
            contentColor = { MaterialTheme.colorScheme.onTertiary },
            background = {
                Brush.linearGradient(
                    0f to MaterialTheme.colorScheme.tertiary,
                    1f to MaterialTheme.colorScheme.tertiary
                )
            },
            border = {
                BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.tertiary)
            }
        )
    }
}