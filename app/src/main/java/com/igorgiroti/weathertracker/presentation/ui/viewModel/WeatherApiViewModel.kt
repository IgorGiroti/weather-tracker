package com.igorgiroti.weathertracker.presentation.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorgiroti.weathertracker.domain.model.Search
import com.igorgiroti.weathertracker.domain.model.Weather
import com.igorgiroti.weathertracker.domain.usecase.GetSearchUseCase
import com.igorgiroti.weathertracker.domain.usecase.GetWeatherApiUseCase
import com.igorgiroti.weathertracker.presentation.ui.state.SearchState
import com.igorgiroti.weathertracker.presentation.ui.state.WeatherUiState
import com.igorgiroti.weathertracker.utils.ResponseStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherApiViewModel(
    private val getWeatherUseCase: GetWeatherApiUseCase,
    private val getSearchUseCase: GetSearchUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState: MutableStateFlow<WeatherUiState<Weather?>> =
        MutableStateFlow(WeatherUiState.Initial)
    val uiState = _uiState

    private val _searchState: MutableStateFlow<SearchState<List<Search>?>> =
        MutableStateFlow(SearchState.Initial)
    val searchState = _searchState

    private lateinit var previousUiState: WeatherUiState<Weather?>


    fun getWeather(city: String) {
        viewModelScope.launch(dispatcher) {
            getWeatherUseCase.execute(city).collect { response ->
                when (response) {
                    is ResponseStatus.Loading -> {
                        _uiState.update { WeatherUiState.Loading }
                    }

                    is ResponseStatus.Success -> {
                        _uiState.update { WeatherUiState.Success(response.data) }
                    }

                    is ResponseStatus.Error -> {
                        _uiState.update { WeatherUiState.Error(response.error) }
                    }
                }
            }
        }
    }

    fun getSearch(city: String) {
        viewModelScope.launch(dispatcher) {
            getSearchUseCase.execute(city).collect { response ->
                when (response) {
                    is ResponseStatus.Loading -> {
                        _searchState.update { SearchState.Loading }
                        if (_uiState.value !is WeatherUiState.Searching) {
                            previousUiState = _uiState.value
                        }
                        _uiState.update { WeatherUiState.Searching }
                    }

                    is ResponseStatus.Success -> {
                        _searchState.update { SearchState.Success(response.data) }
                    }

                    is ResponseStatus.Error -> {
                        _searchState.update { SearchState.Error(response.error) }
                    }
                }
            }
        }
    }

    fun cleanSearchAndRestoreState() {
        _searchState.update { SearchState.Initial }
        if (::previousUiState.isInitialized) {
            _uiState.update { previousUiState }
        }
    }
}
