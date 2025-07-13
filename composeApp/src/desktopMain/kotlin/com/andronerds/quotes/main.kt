package com.andronerds.quotes

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.andronerds.quotes.di.appModule
import com.andronerds.quotes.di.repositoryModule
import com.andronerds.quotes.di.serviceModule
import org.koin.core.context.startKoin
import java.awt.Dimension

fun main() = application {
    startKoin {
        modules(
            serviceModule,
            repositoryModule,
            appModule,
        )
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "QuoteVault",
    ) {
        // Set the minimum size of the window
        window.minimumSize = Dimension(550, 600)

        App()
    }
}