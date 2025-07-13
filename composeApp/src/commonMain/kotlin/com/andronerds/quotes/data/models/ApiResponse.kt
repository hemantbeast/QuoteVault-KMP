package com.andronerds.quotes.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val data: T? = null,
    val message: String? = null,
    val statusCode: Int? = null,
    val success: Boolean? = null,
) {
    val isSuccess: Boolean
        get() = statusCode == 200 && success == true
}

// Custom exception for API-specific errors
class ApiException(message: String) : Exception(message)
