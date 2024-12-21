package com.igorgiroti.weathertracker.presentation.ui.state


sealed interface WeatherUiState<out T> {
    object Loading : WeatherUiState<Nothing>
    object Searching : WeatherUiState<Nothing>
    data class Success<out R>(val data: R) : WeatherUiState<R>
    data class Error(val error: Throwable) : WeatherUiState<Nothing>
}