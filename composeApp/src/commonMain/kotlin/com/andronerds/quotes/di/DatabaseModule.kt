package com.andronerds.quotes.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.andronerds.quotes.data.database.AppDatabase
import com.andronerds.quotes.data.database.getDatabaseBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val databaseModule = module {
    single {
        getDatabaseBuilder()
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    // Defining database access objects (DAO)
    single { get<AppDatabase>().quoteDao() }
}
