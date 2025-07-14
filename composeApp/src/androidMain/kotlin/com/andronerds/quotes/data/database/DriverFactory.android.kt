package com.andronerds.quotes.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

object AndroidDatabaseProvider : KoinComponent {
    private val appContext: Context by inject()

    fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        val dbFile = appContext.getDatabasePath(DB_FILE_NAME)
        return Room.databaseBuilder<AppDatabase>(appContext, dbFile.absolutePath)
    }
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    return AndroidDatabaseProvider.getDatabaseBuilder()
}
