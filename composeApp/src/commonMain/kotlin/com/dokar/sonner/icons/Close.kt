package com.dokar.sonner.icons

import androidx.compose.ui.graphics.vector.ImageVector

// androidx.compose.material.icons.outlined.Close.kt

internal val Close: ImageVector
    get() {
        if (_close != null) {
            return _close!!
        }
        _close = materialIcon(name = "Outlined.Close") {
            materialPath {
                moveTo(19.0f, 6.41f)
                lineTo(17.59f, 5.0f)
                lineTo(12.0f, 10.59f)
                lineTo(6.41f, 5.0f)
                lineTo(5.0f, 6.41f)
                lineTo(10.59f, 12.0f)
                lineTo(5.0f, 17.59f)
                lineTo(6.41f, 19.0f)
                lineTo(12.0f, 13.41f)
                lineTo(17.59f, 19.0f)
                lineTo(19.0f, 17.59f)
                lineTo(13.41f, 12.0f)
                lineTo(19.0f, 6.41f)
                close()
            }
        }
        return _close!!
    }

private var _close: ImageVector? = null
