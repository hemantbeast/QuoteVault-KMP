package com.andronerds.quotes.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable

sealed class UiState<out T> {
    object Loading: UiState<Nothing>()

    data class Success<T>(val data: T): UiState<T>()

    data class Error(val message: String?) : UiState<Nothing>()

    object Empty: UiState<Nothing>()

    fun isLoading() = this is Loading
    fun isSuccess() = this is Success
    fun isError() = this is Error

    fun getSuccessData() = (this as Success).data
    fun getSuccessDataOrNull(): T? {
        return try {
            (this as Success).data
        } catch (e: Exception) {
            null
        }
    }

    fun getErrorMessage() = (this as Error).message
    fun getErrorMessageOrError(): String? {
        return try {
            (this as Error).message
        } catch (e: Exception) {
            e.message
        }
    }
}

@Composable
fun <T> UiStateHandler(
    state: UiState<T>,
    transitionSpec: AnimatedContentTransitionScope<*>.() -> ContentTransform = {
        fadeIn(tween(durationMillis = 300)) togetherWith
                fadeOut(tween(durationMillis = 300))
    },
    onRetry: () -> Unit = {},
    loading: @Composable () -> Unit = { DefaultLoadingContent() },
    error: @Composable (String, () -> Unit) -> Unit = { message, retry ->
        DefaultErrorContent(message, retry)
    },
    empty: @Composable () -> Unit = { DefaultEmptyContent(onRetry) },
    success: @Composable (T) -> Unit,
) {
    AnimatedContent(
        targetState = state,
        transitionSpec = transitionSpec,
        label = "Animated State",
    ) { state ->
        when(state) {
            is UiState.Loading -> loading()
            is UiState.Empty -> empty()
            is UiState.Error -> error(state.message ?: "", onRetry)
            is UiState.Success -> success(state.data)
        }
    }
}