package com.igorgiroti.weathertracker.presentation.ui.state


sealed interface WeatherUiState<out T> {
    object Loading : WeatherUiState<Nothing>
    object Searching : WeatherUiState<Nothing>
    data class Loaded<out R>(val data: R) : WeatherUiState<R>
}