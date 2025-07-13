package com.andronerds.quotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val transparent = Color.Transparent.hashCode()
            val isDarkTheme = isSystemInDarkTheme()

            val systemBarStyle = if (isDarkTheme) {
                SystemBarStyle.dark(transparent)
            } else {
                SystemBarStyle.light(transparent,transparent)
            }

            enableEdgeToEdge(
                statusBarStyle = systemBarStyle,
                navigationBarStyle = systemBarStyle
            )

            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}