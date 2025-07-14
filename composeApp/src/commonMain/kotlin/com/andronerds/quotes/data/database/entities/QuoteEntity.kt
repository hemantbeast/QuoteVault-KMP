package com.andronerds.quotes.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val quote: String,
    val author: String,
    val tags: List<String>,
    val savedOn: Instant,
)
