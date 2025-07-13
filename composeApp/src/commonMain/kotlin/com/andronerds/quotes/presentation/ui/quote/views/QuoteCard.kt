package com.andronerds.quotes.presentation.ui.quote.views

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
import com.andronerds.quotes.utils.ScreenUtils
import com.valentinilk.shimmer.shimmer

@Composable
fun QuoteCard(
    uiState: UiState<QuoteModel>,
    modifier: Modifier = Modifier
) {
    val screenSize = ScreenUtils.getSize()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 200.dp, max = 300.dp)
            .padding(24.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = Color.Black.copy(alpha = 0.3f),
                spotColor = Color.Black.copy(alpha = 0.3f),
            )
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
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
                                .background(Color.LightGray)
                        )
                        Box(
                            modifier = Modifier
                                .height(24.dp)
                                .width((screenSize.width.value * 0.46).dp)
                                .background(Color.LightGray)
                        )
                        Box(
                            modifier = Modifier
                                .height(24.dp)
                                .width((screenSize.width.value * 0.48).dp)
                                .background(Color.LightGray)
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
                            .background(Color.LightGray)
                    )
                }
            ) { quote ->
                AuthorText(
                    author = quote.author ?: "",
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
