package com.example.meritmatch.di

import com.example.meritmatch.backend.MeritMarchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(
            60,
            TimeUnit.SECONDS
        )
        .readTimeout(
            60,
            TimeUnit.SECONDS
        )
        .build()

    @Provides
    @Singleton
    fun meritMatchApi(okHttpClient: OkHttpClient): MeritMarchApi = Retrofit.Builder()
        .baseUrl("http://localhost:8000")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MeritMarchApi::class.java)
}