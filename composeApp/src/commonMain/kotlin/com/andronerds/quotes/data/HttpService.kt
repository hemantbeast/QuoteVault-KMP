package com.andronerds.quotes.data

import com.andronerds.quotes.data.models.ApiException
import com.andronerds.quotes.data.models.ApiResponse
import com.andronerds.quotes.utils.ApiConstants
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpService {
    val client = HttpClient {
        // timeout
        install(HttpTimeout) {
            val timeout = 60000L

            requestTimeoutMillis = timeout
            connectTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }

        // logging
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println("KtorClient: $message")
                }
            }
        }

        // url & headers
        defaultRequest {
            header("Accept", "application/json")
            header("Content-Type", "application/json")
            url(ApiConstants.API_URL)
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    // Helper to process common API responses
    suspend inline fun <reified T> safeApiCall(block: suspend () -> ApiResponse<T>): T {
        val apiResponse = block()

        if (apiResponse.isSuccess) {
            return apiResponse.data ?: throw ApiException(
                apiResponse.message ?: "API returned success but result was null."
            )
        } else {
            throw ApiException(apiResponse.message ?: "Unknown API error")
        }
    }
}
