package com.example.mocktestapp.presentation.ui.live_test

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mocktestapp.data.module.MockTestModule
import com.example.mocktestapp.data.repository.MockTestRepository
import com.example.mocktestapp.presentation.ui.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LiveTestViewModel @Inject constructor(
    private val repo: MockTestRepository
) : ViewModel() {
    private val TAG = javaClass.simpleName

    private val _liveTest = MutableStateFlow<List<MockTestModule>?>(null)
    val listTest = _liveTest.asStateFlow()


    init {
        fetchLiveTest()
    }

    private fun fetchLiveTest() = viewModelScope.launch(Dispatchers.IO) {

        val response = repo.fetchLiveTest()
        if (!response.isSuccessful) {
            return@launch
        }
        val result = response.body()
        _liveTest.emit(result!!.data)
        Log.i(TAG, "fetchLiveTest: ${result.data}")
    }


}