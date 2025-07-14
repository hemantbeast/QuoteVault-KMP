package com.andronerds.quotes.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val appDataDir = File(System.getProperty("user.home"), ".quotevault/databases")

    if (!appDataDir.exists()) {
        appDataDir.mkdirs()
    }

    val dbFile = File(appDataDir, DB_FILE_NAME)
    return Room.databaseBuilder<AppDatabase>(dbFile.absolutePath)
}
