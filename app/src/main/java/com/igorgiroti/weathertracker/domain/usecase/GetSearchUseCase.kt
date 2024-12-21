package com.igorgiroti.weathertracker.domain.usecase

import com.igorgiroti.weathertracker.domain.model.Search
import com.igorgiroti.weathertracker.domain.repository.WeatherApiRepository
import com.igorgiroti.weathertracker.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow

class GetSearchUseCase (private val weatherRepository: WeatherApiRepository) {
    suspend fun execute(city: String): Flow<ResponseStatus<List<Search>?>> {
        return weatherRepository.getSearchWeather(city)
    }
}
