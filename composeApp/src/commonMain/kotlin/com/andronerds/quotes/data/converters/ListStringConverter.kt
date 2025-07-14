package com.andronerds.quotes.data.converters

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class ListStringConverter {
    @TypeConverter
    fun fromListString(list: List<String>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun toListString(jsonString: String): List<String> {
        return Json.decodeFromString(jsonString)
    }
}
