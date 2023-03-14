package com.example.mocktestapp.domain.di

import com.example.mocktestapp.data.api.MyTestApi
import com.example.mocktestapp.util.Const
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request =
                    chain.request().newBuilder().addHeader("Authorization", "Bearer ${Const.Token}")
                        .build()
                chain.proceed(request)
            }.build())
            .build()
    }


    @Provides
    @Singleton
    fun provideMyTestapi(retrofit: Retrofit): MyTestApi {
        return retrofit.create(MyTestApi::class.java)
    }


}