package com.example.mocktestapp.data.api

import com.example.mocktestapp.data.module.ConfigurationModule
import com.example.mocktestapp.data.module.MockTestModule
import com.example.mocktestapp.data.module.TestPackageModule
import com.example.mocktestapp.presentation.ui.util.ApiResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface MyTestApi {


    @GET("mock-tests")
    suspend fun getMockTest(): Response<ApiResult<List<MockTestModule>>>

    @GET("test-packages")
    suspend fun getTestPackage(): Response<ApiResult<List<TestPackageModule>>>

    @GET("configuration")
    suspend fun getConfiguration(): Response<ApiResult<ConfigurationModule>>

}