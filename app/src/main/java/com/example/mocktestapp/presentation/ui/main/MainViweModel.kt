package com.example.mocktestapp.presentation.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mocktestapp.data.module.ConfigurationModule
import com.example.mocktestapp.data.repository.MockTestRepository
import com.example.mocktestapp.presentation.ui.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViweModel @Inject constructor(
    private val repo: MockTestRepository
) : ViewModel() {

    private val TAG = javaClass.simpleName

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()
    private val _configurationData = MutableStateFlow<ConfigurationModule?>(null)
    val configuration = _configurationData

    init {
        fetchConfiguration()
    }

    private fun fetchConfiguration() = viewModelScope.launch(Dispatchers.IO) {
        _uiState.emit(UiState.Loading)
        val response = repo.fetchconfiguration()
        if (!response.isSuccessful) {
            _uiState.emit(UiState.Failed)
            return@launch
        }
        val result = response.body()
        _configurationData.emit(result!!.data)
        Log.i(TAG, "fetchConfiguration: ${result.data} ")
        _uiState.emit(UiState.Success)
    }


}