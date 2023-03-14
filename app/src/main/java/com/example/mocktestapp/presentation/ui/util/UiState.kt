package com.example.mocktestapp.presentation.ui.util

sealed class UiState {

    object Loading : UiState()
    object Success : UiState()
    object Failed : UiState()


}
