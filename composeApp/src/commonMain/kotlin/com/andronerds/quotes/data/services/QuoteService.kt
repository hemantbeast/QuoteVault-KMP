package com.andronerds.quotes.data.services

import com.andronerds.quotes.data.HttpService
import com.andronerds.quotes.data.models.ApiResponse
import com.andronerds.quotes.data.models.QuoteModel
import com.andronerds.quotes.utils.ApiConstants
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.encodedPath

interface QuoteService {
    suspend fun getQuote(): QuoteModel
}

internal class QuoteServiceImpl(private val httpService: HttpService) : QuoteService {
    // [GET] Quote
    override suspend fun getQuote(): QuoteModel {
        return httpService.safeApiCall {
            httpService.client.get {
                url {
                    encodedPath = ApiConstants.QUOTES_URL
                }
            }.body<ApiResponse<QuoteModel>>()
        }
    }
}
