package com.igorgiroti.weathertracker.domain.model

data class Weather(
    val location: Location,
    val weather: CurrentWeather
)

data class Location(
    val name: String?
)

data class CurrentWeather(
    val temp: Double?,
    val humidity: Int?,
    val feelsLike: Double?,
    val uv: Double?,
    val condition: CurrentWeatherCondition,
)

data class CurrentWeatherCondition(
    val text: String?,
    val iconUrl: String?
)
