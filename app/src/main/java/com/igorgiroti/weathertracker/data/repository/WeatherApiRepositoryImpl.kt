package com.igorgiroti.weathertracker.data.repository

import com.igorgiroti.weathertracker.data.mapper.toDomain
import com.igorgiroti.weathertracker.data.service.WeatherApiService
import com.igorgiroti.weathertracker.domain.model.Search
import com.igorgiroti.weathertracker.domain.model.Weather
import com.igorgiroti.weathertracker.domain.repository.WeatherApiRepository
import com.igorgiroti.weathertracker.utils.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow


class WeatherApiRepositoryImpl(
    private val weatherApiService: WeatherApiService
) : WeatherApiRepository {
    override suspend fun getCurrentWeather(city: String): Flow<ResponseStatus<Weather?>> =
        flow {
            emit(ResponseStatus.Loading)
            val result = weatherApiService.getCurrentWeather(city = city)
            when (result.code()) {
                200 -> emit(ResponseStatus.Success(result.body()?.toDomain()))
                else -> emit(
                    ResponseStatus.Error(
                        Throwable(message = result.message())
                    )
                )
            }
        }.catch { exception ->
            emit(ResponseStatus.Error(exception))
        }


    override suspend fun getSearchWeather(city: String): Flow<ResponseStatus<List<Search>?>> =
        flow {
            emit(ResponseStatus.Loading)
            val result = weatherApiService.getSearchWeather(search = city)
            when (result.code()) {
                200 -> emit(ResponseStatus.Success(result.body()?.toDomain()))
                else -> emit(
                    ResponseStatus.Error(
                        Throwable(message = result.message())
                    )
                )
            }
        }.catch { exception ->
            emit(ResponseStatus.Error(exception))
        }
}
