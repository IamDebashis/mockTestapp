package com.example.mocktestapp.data.repository

import com.example.mocktestapp.data.module.ConfigurationModule
import com.example.mocktestapp.data.module.MockTestModule
import com.example.mocktestapp.data.module.TestPackageModule
import com.example.mocktestapp.presentation.ui.util.ApiResult
import retrofit2.Response

interface MockTestRepository {

    suspend fun fetchconfiguration(): Response<ApiResult<ConfigurationModule>>
    suspend fun fetchTestPackage(): Response<ApiResult<List<TestPackageModule>>>
    suspend fun fetchLiveTest(): Response<ApiResult<List<MockTestModule>>>


}