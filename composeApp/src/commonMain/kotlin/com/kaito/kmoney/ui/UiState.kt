package com.kaito.kmoney.ui

sealed class UiState <out T> {
    data object Init: UiState<Nothing>()
    data object Loading: UiState<Nothing>()

    data class Result<T>(val data: T): UiState<T>()
    data class Error(val throwable: Throwable, val msg: String): UiState<Nothing>()
}