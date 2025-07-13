package com.andronerds.quotes

import androidx.compose.ui.window.ComposeUIViewController
import com.andronerds.quotes.di.appModule
import com.andronerds.quotes.di.repositoryModule
import com.andronerds.quotes.di.serviceModule
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    startKoin {
        modules(
            serviceModule,
            repositoryModule,
            appModule,
        )
    }

    return ComposeUIViewController { App() }
}