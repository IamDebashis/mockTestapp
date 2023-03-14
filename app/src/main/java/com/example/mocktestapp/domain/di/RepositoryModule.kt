package com.example.mocktestapp.domain.di

import com.example.mocktestapp.data.api.MyTestApi
import com.example.mocktestapp.data.repository.MockTestRepository
import com.example.mocktestapp.domain.repository_impl.MockTestRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMockTestRepository(api: MyTestApi): MockTestRepository {
        return MockTestRepositoryImpl(api)
    }


}