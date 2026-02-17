package com.andronerds.quotes.domain.mappers

import com.andronerds.quotes.data.database.entities.QuoteEntity
import com.andronerds.quotes.data.models.QuoteModel
import kotlinx.datetime.Clock

fun QuoteModel.toEntity(): QuoteEntity {
    return QuoteEntity(
        id = this.id ?: 0,
        quote = this.content ?: "",
        author = this.author ?: "",
        tags = this.tags ?: listOf(),
        savedOn = Clock.System.now(),
    )
}

fun QuoteEntity.toModel(): QuoteModel {
    return QuoteModel(
        id = this.id,
        content = this.quote,
        author = this.author,
        tags = this.tags,
        dateAdded = this.savedOn.toString(),
        dateModified = this.savedOn.toString(),
        authorSlug = this.author.lowercase().replace(" ", "-"),
        length = this.quote.length
    )
}
