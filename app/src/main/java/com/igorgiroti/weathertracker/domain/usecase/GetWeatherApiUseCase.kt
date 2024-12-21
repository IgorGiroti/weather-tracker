package com.igorgiroti.weathertracker.domain.usecase

import com.igorgiroti.weathertracker.domain.model.Weather
import com.igorgiroti.weathertracker.domain.repository.WeatherApiRepository
import com.igorgiroti.weathertracker.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

class GetWeatherApiUseCase(private val weatherRepository: WeatherApiRepository) {
    suspend fun execute(city: String): Flow<ResponseStatus<Weather?>> {
        return weatherRepository.getCurrentWeather(city)
    }
}
