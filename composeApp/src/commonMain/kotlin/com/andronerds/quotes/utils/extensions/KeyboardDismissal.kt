package com.andronerds.quotes.utils.extensions

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager

/**
 * Modifier to dismiss the keyboard and clear focus when tapping outside a focused TextField.
 * Apply this to the root composable of your screen.
 */
@Composable
fun Modifier.clearFocusOnTapOutside(): Modifier {
    val focusManager = LocalFocusManager.current

    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            focusManager.clearFocus()
        })
    }
}