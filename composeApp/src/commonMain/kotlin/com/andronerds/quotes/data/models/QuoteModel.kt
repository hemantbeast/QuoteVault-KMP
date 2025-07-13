package com.andronerds.quotes.data.models

import kotlinx.serialization.Serializable

@Serializable
data class QuoteModel(
    val author: String? = null,
    val authorSlug: String? = null,
    val content: String? = null,
    val dateAdded: String? = null,
    val dateModified: String? = null,
    val id: Int? = null,
    val length: Int? = null,
    val tags: List<String>? = null,
)
