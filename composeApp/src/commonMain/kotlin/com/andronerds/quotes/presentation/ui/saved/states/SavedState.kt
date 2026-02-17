package com.andronerds.quotes.presentation.ui.saved.states

import com.andronerds.quotes.data.database.entities.QuoteEntity
import com.andronerds.quotes.presentation.components.UiState

data class SavedState(
    val data: UiState<List<QuoteEntity>> = UiState.Loading,
)
