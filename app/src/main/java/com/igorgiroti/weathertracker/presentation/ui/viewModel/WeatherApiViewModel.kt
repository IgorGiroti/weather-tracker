package com.igorgiroti.weathertracker.presentation.ui.viewModel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorgiroti.weathertracker.domain.model.Search
import com.igorgiroti.weathertracker.domain.model.Weather
import com.igorgiroti.weathertracker.domain.usecase.GetSearchUseCase
import com.igorgiroti.weathertracker.domain.usecase.GetWeatherApiUseCase
import com.igorgiroti.weathertracker.presentation.ui.state.SearchState
import com.igorgiroti.weathertracker.presentation.ui.state.WeatherUiState
import com.igorgiroti.weathertracker.utils.ResponseStatus
import com.igorgiroti.weathertracker.utils.SAVED_CITY
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherApiViewModel(
    private val getWeatherUseCase: GetWeatherApiUseCase,
    private val getSearchUseCase: GetSearchUseCase,
    private val dispatcher: CoroutineDispatcher,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _uiState: MutableStateFlow<WeatherUiState<Weather?>> =
        MutableStateFlow(WeatherUiState.Loaded(null))
    val uiState = _uiState

    private val _searchState: MutableStateFlow<SearchState<List<Search>?>> =
        MutableStateFlow(SearchState.Initial)
    val searchState = _searchState

    private val _showErrorBottomSheet: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showErrorBottomSheet = _showErrorBottomSheet

    private lateinit var previousSuccessUiState: WeatherUiState<Weather?>

    init {
        val savedCity = getSavedWeather()
        savedCity?.let { city ->
            getWeather(city)
        }
    }

    fun getWeather(city: String) {
        viewModelScope.launch(dispatcher) {
            getWeatherUseCase.execute(city).collect { response ->
                when (response) {
                    is ResponseStatus.Loading -> {
                        _uiState.update { WeatherUiState.Loading }
                    }

                    is ResponseStatus.Success -> {
                        _uiState.update { WeatherUiState.Loaded(response.data) }
                        _searchState.update { SearchState.Initial }
                        saveCity(city)
                        previousSuccessUiState = _uiState.value
                    }

                    is ResponseStatus.Error -> {
                        _showErrorBottomSheet.update { true }
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
                        _uiState.update { WeatherUiState.Searching }
                    }

                    is ResponseStatus.Success -> {
                        _searchState.update { SearchState.Success(response.data) }
                    }

                    is ResponseStatus.Error -> {
                        _showErrorBottomSheet.update { true }
                        _searchState.update { SearchState.Initial }
                        _uiState.update { previousSuccessUiState }
                    }
                }
            }
        }
    }

    fun cleanSearchAndRestoreState() {
        _searchState.update { SearchState.Initial }
        if (::previousSuccessUiState.isInitialized) {
            _uiState.update { previousSuccessUiState }
        }
    }

    fun dismissBottomSheet() {
        _showErrorBottomSheet.update { false }
    }


    private fun saveCity(city: String) {
        sharedPreferences.edit().putString(SAVED_CITY, city).apply()
    }


    private fun getSavedWeather(): String? {
        return sharedPreferences.getString(SAVED_CITY, null)
    }
}
