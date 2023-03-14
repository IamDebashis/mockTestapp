package com.example.mocktestapp.presentation.ui.test_package

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mocktestapp.data.module.TestPackageModule
import com.example.mocktestapp.data.repository.MockTestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestPackgeViewModel @Inject constructor(
    private val repo: MockTestRepository
) : ViewModel() {

    private val TAG = javaClass.simpleName

    private val _testPackage = MutableStateFlow<List<TestPackageModule>?>(null)
    val testPackage = _testPackage.asStateFlow()

    init {
        fetchTestPackage()
    }


    private fun fetchTestPackage() = viewModelScope.launch(Dispatchers.IO) {

        val response = repo.fetchTestPackage()
        if (!response.isSuccessful) {
            return@launch
        }
        val result = response.body()
        _testPackage.emit(result!!.data)
        Log.i(TAG, "fetchLiveTest: ${result.data}")
    }


}