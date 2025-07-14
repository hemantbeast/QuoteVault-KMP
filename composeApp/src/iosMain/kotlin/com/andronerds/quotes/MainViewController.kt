package com.andronerds.quotes

import androidx.compose.ui.window.ComposeUIViewController
import com.andronerds.quotes.di.*
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    startKoin {
        modules(
            databaseModule,
            serviceModule,
            repositoryModule,
            appModule,
        )
    }

    return ComposeUIViewController { App() }
}