package com.igorgiroti.weathertracker.domain.repository

import com.igorgiroti.weathertracker.domain.model.Weather
import com.igorgiroti.weathertracker.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

interface WeatherApiRepository {
    suspend fun getCurrentWeather(city: String): Flow<ResponseStatus<Weather?>>
}
