package com.andronerds.quotes.presentation.ui.quote.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.andronerds.quotes.presentation.theme.lightLavender
import com.andronerds.quotes.presentation.theme.lightPurple
import com.andronerds.quotes.presentation.theme.primaryPurple
import com.andronerds.quotes.presentation.theme.shadowBlack
import com.andronerds.quotes.presentation.theme.surfaceWhite
import com.andronerds.quotes.presentation.theme.textPrimaryDark
import com.andronerds.quotes.presentation.theme.textSecondaryGray
import com.andronerds.quotes.presentation.theme.transparent
import com.andronerds.quotes.presentation.ui.saved.SavedScreen
import com.andronerds.quotes.utils.shareText
import org.jetbrains.compose.resources.stringResource
import quotes.composeapp.generated.resources.Res
import quotes.composeapp.generated.resources.*

@Composable
fun DrawerContent(
    onCloseDrawer: () -> Unit,
    currentRoute: String = "home"
) {
    val navigator = LocalNavigator.currentOrThrow

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .widthIn(min = 280.dp, max = 320.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        // Header Section with Gradient
        DrawerHeader()

        // Menu Items Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Menu",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // Home Item
            DrawerMenuItem(
                icon = Icons.Filled.Home,
                label = stringResource(Res.string.home),
                isSelected = currentRoute == "home",
                onClick = {
                    onCloseDrawer()
                }
            )

            // Saved Quotes Item
            DrawerMenuItem(
                icon = Icons.Filled.Bookmark,
                label = stringResource(Res.string.saved),
                badge = null, // Could show count of saved quotes
                isSelected = currentRoute == "saved",
                onClick = {
                    onCloseDrawer()
                    navigator.push(SavedScreen())
                }
            )

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
            )

            Text(
                text = "Preferences",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // Settings Item
            DrawerMenuItem(
                icon = Icons.Filled.Settings,
                label = stringResource(Res.string.settings),
                isSelected = currentRoute == "settings",
                onClick = {
                    onCloseDrawer()
                    // TODO: Navigate to settings
                }
            )

            // Share App Item
            DrawerMenuItem(
                icon = Icons.Filled.Share,
                label = stringResource(Res.string.share_app),
                onClick = {
                    onCloseDrawer()
                    shareText(
                        text = "Check out QuoteVault - Your daily dose of inspiration!",
                        subject = "QuoteVault App"
                    )
                }
            )

            // About Item
            DrawerMenuItem(
                icon = Icons.Filled.Info,
                label = stringResource(Res.string.about),
                isSelected = currentRoute == "about",
                onClick = {
                    onCloseDrawer()
                    // TODO: Navigate to about
                }
            )
        }

        // Footer Section
        DrawerFooter()
    }
}

@Composable
private fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        lightLavender,
                        lightPurple.copy(alpha = 0.9f),
                        primaryPurple.copy(alpha = 0.8f),
                    ),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            // User Avatar with white background for consistency
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = CircleShape,
                        ambientColor = shadowBlack.copy(alpha = 0.2f),
                        spotColor = shadowBlack.copy(alpha = 0.2f)
                    )
                    .background(
                        color = surfaceWhite,
                        shape = CircleShape
                    )
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = primaryPurple
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // App Name - dark text for contrast on light background
            Text(
                text = stringResource(Res.string.quote_vault),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                ),
                color = textPrimaryDark
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Subtitle
            Text(
                text = stringResource(Res.string.daily_inspiration),
                style = MaterialTheme.typography.bodyMedium,
                color = textSecondaryGray
            )
        }
    }
}

@Composable
private fun DrawerMenuItem(
    icon: ImageVector,
    label: String,
    badge: String? = null,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            transparent
        },
        label = "background_color"
    )

    val contentColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.onPrimaryContainer
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        },
        label = "content_color"
    )

    val iconColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        },
        label = "icon_color"
    )

    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.02f else 1f,
        label = "scale"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .scale(scale)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        // Icon
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (isSelected) {
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                    } else {
                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Label
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
            ),
            color = contentColor,
            modifier = Modifier.weight(1f)
        )

        // Badge
        if (badge != null) {
            Badge(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Text(text = badge, style = MaterialTheme.typography.labelSmall)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        // Selection Indicator
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
    }
}

@Composable
private fun DrawerFooter() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.version),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(Res.string.made_with_love),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
        )
    }
}
