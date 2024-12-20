package com.igorgiroti.weathertracker.data.mapper

import com.igorgiroti.weathertracker.data.model.CurrentWeatherConditionResponse
import com.igorgiroti.weathertracker.data.model.CurrentWeatherResponse
import com.igorgiroti.weathertracker.data.model.LocationResponse
import com.igorgiroti.weathertracker.data.model.WeatherApiResponse
import com.igorgiroti.weathertracker.domain.model.CurrentWeather
import com.igorgiroti.weathertracker.domain.model.CurrentWeatherCondition
import com.igorgiroti.weathertracker.domain.model.Location
import com.igorgiroti.weathertracker.domain.model.Weather

fun WeatherApiResponse.toDomain() = Weather(
    location = this.locationResponse.toDomain(),
    weather = this.weather.toDomain()
)

fun LocationResponse.toDomain() = Location(
    name = this.name
)

fun CurrentWeatherResponse.toDomain() = CurrentWeather(
    temp = this.temp,
    humidity = this.humidity,
    feelsLike = this.feelsLike,
    uv = this.uv,
    condition = this.condition.toDomain(),
)

fun CurrentWeatherConditionResponse.toDomain() = CurrentWeatherCondition(
    text = this.text,
    iconUrl = this.iconUrl
)
