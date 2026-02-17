package com.andronerds.quotes.presentation.ui.saved.viewModels

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.andronerds.quotes.data.database.entities.QuoteEntity
import com.andronerds.quotes.domain.repositories.QuoteRepository
import com.andronerds.quotes.presentation.components.UiState
import com.andronerds.quotes.presentation.ui.saved.states.SavedState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedViewModel(private val repository: QuoteRepository): ScreenModel {
    private val _state = MutableStateFlow(SavedState())
    val state: StateFlow<SavedState> = _state.asStateFlow()

    fun getSavedData() {
        screenModelScope.launch {
            repository.getSavedQuotes().collectLatest { entities ->
                _state.update {
                    if (entities.isEmpty()) {
                        it.copy(data = UiState.Empty)
                    } else {
                        it.copy(data = UiState.Success(entities))
                    }
                }
            }
        }
    }

    fun deleteQuote(quote: QuoteEntity) {
        screenModelScope.launch {
            repository.removeQuoteEntity(quote)
        }
    }
}