package com.andronerds.quotes.data.repositories

import com.andronerds.quotes.data.database.dao.QuoteDao
import com.andronerds.quotes.data.database.entities.QuoteEntity
import com.andronerds.quotes.data.models.ApiException
import com.andronerds.quotes.data.models.QuoteModel
import com.andronerds.quotes.data.services.QuoteService
import com.andronerds.quotes.domain.mappers.toEntity
import com.andronerds.quotes.domain.repositories.QuoteRepository
import kotlinx.coroutines.flow.Flow

internal class QuoteRepositoryImpl(
    private val quoteService: QuoteService,
    private val quoteDao: QuoteDao
): QuoteRepository {
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

    // Save quote to database
    override suspend fun saveQuote(quote: QuoteModel) {
        val entity = quote.toEntity()
        quoteDao.insert(entity)
    }

    // Remove quote from database
    override suspend fun removeQuote(quote: QuoteModel) {
        val entity = quote.toEntity()
        quoteDao.delete(entity)
    }

    // Remove quote entity from database directly
    override suspend fun removeQuoteEntity(quote: QuoteEntity) {
        quoteDao.delete(quote)
    }

    override fun getSavedQuotes(): Flow<List<QuoteEntity>> {
        return quoteDao.getAll()
    }
}
