package com.andronerds.quotes.presentation.ui.quote.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.andronerds.quotes.data.models.QuoteModel
import com.andronerds.quotes.presentation.components.UiState
import com.andronerds.quotes.presentation.components.UiStateHandler
import com.andronerds.quotes.presentation.theme.shimmerLightGray
import com.andronerds.quotes.utils.ScreenUtils
import com.valentinilk.shimmer.shimmer

@Composable
fun QuoteCard(
    uiState: UiState<QuoteModel>,
    saved: Boolean,
    onSaved: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val screenSize = ScreenUtils.getSize()

    Card(
        modifier = modifier.padding(horizontal = 24.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
            pressedElevation = 8.dp
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp, max = 400.dp)
                .padding(24.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(24.dp)
            ) {
                UiStateHandler(
                    state = uiState,
                    loading = {
                        Column(
                            modifier = Modifier.shimmer(),
                            verticalArrangement = Arrangement.spacedBy(7.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(24.dp)
                                    .width((screenSize.width.value * 0.5).dp)
                                    .background(shimmerLightGray)
                            )
                            Box(
                                modifier = Modifier
                                    .height(24.dp)
                                    .width((screenSize.width.value * 0.46).dp)
                                    .background(shimmerLightGray)
                            )
                            Box(
                                modifier = Modifier
                                    .height(24.dp)
                                    .width((screenSize.width.value * 0.48).dp)
                                    .background(shimmerLightGray)
                            )
                        }
                    }
                ) { quote ->
                    QuoteText(
                        quote = "\"${quote.content}\"",
                        modifier = Modifier.weight(1f, fill = false)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                UiStateHandler(
                    state = uiState,
                    loading = {
                        Box(
                            modifier = Modifier
                                .height(12.dp)
                                .width((screenSize.width.value * 0.2).dp)
                                .shimmer()
                                .background(shimmerLightGray)
                        )
                    }
                ) { quote ->
                    AuthorText(
                        author = quote.author ?: "",
                    )
                }
            }
            IconButton(
                onClick = onSaved,
                modifier = Modifier.align(BottomEnd)
            ) {
                val icon = if (saved) {
                    Icons.Filled.Bookmark
                } else {
                    Icons.Filled.BookmarkBorder
                }

                Icon(
                    imageVector = icon,
                    contentDescription = "Save Quote"
                )
            }
        }
    }
}

@Composable
private fun QuoteText(
    quote: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = quote,
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        fontStyle = FontStyle.Italic,
        lineHeight = 1.4.em,
        overflow = TextOverflow.Visible,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier
    )
}

@Composable
private fun AuthorText(
    author: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "- $author",
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        overflow = TextOverflow.Ellipsis,
        fontSize = 14.sp,
        fontWeight = FontWeight.W500,
        maxLines = 1,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}
