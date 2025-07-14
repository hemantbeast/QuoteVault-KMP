package com.andronerds.quotes

import android.app.Application
import com.andronerds.quotes.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            androidLogger(Level.ERROR)

            modules(
                databaseModule,
                serviceModule,
                repositoryModule,
                appModule,
            )
        }
    }
}