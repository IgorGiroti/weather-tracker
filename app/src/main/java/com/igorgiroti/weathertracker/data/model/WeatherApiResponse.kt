package com.igorgiroti.weathertracker.data.model

import com.google.gson.annotations.SerializedName


data class WeatherApiResponse(
    @SerializedName("location")
    val locationResponse: LocationResponse,
    @SerializedName("current")
    val weather: CurrentWeatherResponse
)

data class LocationResponse(
    @SerializedName("name")
    val name: String,
)

data class CurrentWeatherResponse(
    @SerializedName("temp_f")
    val temp: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("feelslike_f")
    val feelsLike: Double,
    @SerializedName("uv")
    val uv: Double,
    @SerializedName("condition")
    val condition: CurrentWeatherConditionResponse,
)

data class CurrentWeatherConditionResponse(
    @SerializedName("text")
    val text: String?,
    @SerializedName("icon")
    val iconUrl: String,
)
