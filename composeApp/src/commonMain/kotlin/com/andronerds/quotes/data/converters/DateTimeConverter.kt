package com.andronerds.quotes.data.converters

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

class DateTimeConverter {
    @TypeConverter
    fun fromInstant(instant: Instant): Long = instant.toEpochMilliseconds()

    @TypeConverter
    fun toInstant(epochMillis: Long): Instant = Instant.fromEpochMilliseconds(epochMillis)
}
