package com.andronerds.quotes.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.DpSize

object ScreenUtils {

    @Composable
    fun getSize(): DpSize {
        val windowInfo = LocalWindowInfo.current
        val containerSize = windowInfo.containerSize // Size in pixels

        // Convert pixel size to Dp (Density-independent Pixels)
        val density = LocalDensity.current
        val screenWidth = with(density) { containerSize.width.toDp() }
        val screenHeight = with(density) { containerSize.height.toDp() }

        if (LocalLayoutDirection.current.name.contains("landscape", true)) {
            return DpSize(screenHeight, screenWidth)
        }

        return DpSize(screenWidth, screenHeight)
    }
}