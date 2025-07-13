package com.andronerds.quotes.data.repositories

import com.andronerds.quotes.data.models.ApiException
import com.andronerds.quotes.data.models.QuoteModel
import com.andronerds.quotes.data.services.QuoteService
import com.andronerds.quotes.domain.repositories.QuoteRepository

internal class QuoteRepositoryImpl(private val quoteService: QuoteService): QuoteRepository {
    // Get quote
    override suspend fun getQuote(): Result<QuoteModel> {
        return try {
            val result = quoteService.getQuote()

            Result.success(result)
        } catch (ex: ApiException) {
            // Send API related errors to the caller
            Result.failure(ex)
        } catch (ex: Exception) {
            // Send unknown errors to the caller
            Result.failure(ex)
        }
    }
}
