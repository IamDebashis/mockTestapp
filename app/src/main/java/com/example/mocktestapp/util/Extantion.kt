package com.example.mocktestapp.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

fun <T> Fragment.observeFlow(data: Flow<T>, block: suspend (T) -> Unit) {
    lifecycleScope.launchWhenStarted {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            data.collectLatest {
                block.invoke(it)
            }
        }
    }
}

fun <T> AppCompatActivity.observeFlow(data: Flow<T>, block: suspend (T) -> Unit) {
    lifecycleScope.launchWhenCreated {
        repeatOnLifecycle(Lifecycle.State.CREATED) {
            data.collectLatest {
                block(it)
            }
        }
    }
}