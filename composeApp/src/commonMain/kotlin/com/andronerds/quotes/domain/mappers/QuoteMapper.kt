package com.andronerds.quotes.domain.mappers

import com.andronerds.quotes.data.database.entities.QuoteEntity
import com.andronerds.quotes.data.models.QuoteModel
import kotlinx.datetime.Clock

fun QuoteModel.toEntity(): QuoteEntity {
    return QuoteEntity(
        quote = this.content ?: "",
        author = this.author ?: "",
        tags = this.tags ?: listOf(),
        savedOn = Clock.System.now(),
    )
}
