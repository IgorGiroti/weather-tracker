package com.igorgiroti.weathertracker.data.service


import com.igorgiroti.weathertracker.BuildConfig
import com.igorgiroti.weathertracker.data.model.WeatherApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiService {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") city: String,
    ): Response<WeatherApiResponse>
}
