package com.andronerds.quotes.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.andronerds.quotes.data.converters.DateTimeConverter
import com.andronerds.quotes.data.converters.ListStringConverter
import com.andronerds.quotes.data.database.dao.QuoteDao
import com.andronerds.quotes.data.database.entities.QuoteEntity

@TypeConverters(DateTimeConverter::class, ListStringConverter::class)
@ConstructedBy(AppDatabaseConstructor::class)
@Database(entities = [QuoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

internal const val DB_FILE_NAME = "quotevault.db"
