package com.igorgiroti.weathertracker.data.model

import com.google.gson.annotations.SerializedName


data class WeatherApiSearchResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("country")
    val country: String
)
