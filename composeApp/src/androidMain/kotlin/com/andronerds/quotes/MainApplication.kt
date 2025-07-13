package com.andronerds.quotes

import android.app.Application
import com.andronerds.quotes.di.appModule
import com.andronerds.quotes.di.repositoryModule
import com.andronerds.quotes.di.serviceModule
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
                serviceModule,
                repositoryModule,
                appModule,
            )
        }
    }
}