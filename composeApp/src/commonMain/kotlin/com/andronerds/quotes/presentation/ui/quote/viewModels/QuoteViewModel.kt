package com.andronerds.quotes.presentation.ui.quote.viewModels

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.andronerds.quotes.data.models.QuoteModel
import com.andronerds.quotes.domain.repositories.QuoteRepository
import com.andronerds.quotes.presentation.components.UiState
import com.andronerds.quotes.utils.copyToClipboard
import com.andronerds.quotes.utils.shareText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import quotes.composeapp.generated.resources.Res
import quotes.composeapp.generated.resources.quote_vault

class QuoteViewModel(private val repository: QuoteRepository): ScreenModel {
    private val _uiState = MutableStateFlow<UiState<QuoteModel>>(UiState.Loading)
    val uiState: StateFlow<UiState<QuoteModel>> = _uiState.asStateFlow()

    fun getQuote() {
        screenModelScope.launch {
            if (!_uiState.value.isLoading()) {
                _uiState.value = UiState.Loading
            }

            // Get a new quote
            val result = repository.getQuote()

            result.onSuccess { value ->
                _uiState.value = UiState.Success(value)
            }.onFailure { exception ->
                _uiState.value = UiState.Error(exception.message)
            }
        }
    }

    fun copyQuote() {
        if (_uiState.value.isLoading() || _uiState.value.isError()) {
            return
        }

        val item = _uiState.value.getSuccessData()
        copyToClipboard("\"${item.content}\"\n- ${item.author}")
    }

    fun shareQuote() {
        if (_uiState.value.isLoading() || _uiState.value.isError()) {
            return
        }

        screenModelScope.launch {
            val item = _uiState.value.getSuccessData()
            shareText("\"${item.content}\"\n- ${item.author}", getString(Res.string.quote_vault))
        }
    }
}