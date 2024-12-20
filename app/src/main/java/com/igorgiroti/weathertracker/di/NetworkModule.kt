package com.igorgiroti.weathertracker.di

import com.igorgiroti.weathertracker.utils.BASE_URL
import com.igorgiroti.weathertracker.data.service.WeatherApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object NetworkModule {
    val networkModule = module {
        single { provideHttpClient() }
        single { provideConverterFactory() }
        single { provideRetrofit(get(), get()) }
        single { provideService(get()) }
    }

    private fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    private fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    private fun provideService(retrofit: Retrofit): WeatherApiService =
        retrofit.create(WeatherApiService::class.java)
}
