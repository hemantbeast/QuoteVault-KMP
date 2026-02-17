package com.andronerds.quotes.domain.repositories

import com.andronerds.quotes.data.database.entities.QuoteEntity
import com.andronerds.quotes.data.models.QuoteModel
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    suspend fun getQuote(): Result<QuoteModel>

    suspend fun saveQuote(quote: QuoteModel)

    suspend fun removeQuote(quote: QuoteModel)

    suspend fun removeQuoteEntity(quote: QuoteEntity)

    fun getSavedQuotes(): Flow<List<QuoteEntity>>
}
