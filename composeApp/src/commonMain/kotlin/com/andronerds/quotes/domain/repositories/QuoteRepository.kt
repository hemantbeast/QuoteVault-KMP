package com.andronerds.quotes.domain.repositories

import com.andronerds.quotes.data.models.QuoteModel

interface QuoteRepository {
    suspend fun getQuote(): Result<QuoteModel>
}
