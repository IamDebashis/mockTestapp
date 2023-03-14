package com.example.mocktestapp.domain.repository_impl

import com.example.mocktestapp.data.api.MyTestApi
import com.example.mocktestapp.data.module.ConfigurationModule
import com.example.mocktestapp.data.module.MockTestModule
import com.example.mocktestapp.data.module.TestPackageModule
import com.example.mocktestapp.data.repository.MockTestRepository
import com.example.mocktestapp.presentation.ui.util.ApiResult
import retrofit2.Response

class MockTestRepositoryImpl(private val api: MyTestApi) : MockTestRepository {

    override suspend fun fetchconfiguration(): Response<ApiResult<ConfigurationModule>> {
        return api.getConfiguration()
    }

    override suspend fun fetchTestPackage(): Response<ApiResult<List<TestPackageModule>>> {
        return api.getTestPackage()
    }

    override suspend fun fetchLiveTest(): Response<ApiResult<List<MockTestModule>>> {
        return api.getMockTest()
    }
}