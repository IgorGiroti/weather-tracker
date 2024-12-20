package com.igorgiroti.weathertracker.presentation.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorgiroti.weathertracker.domain.model.Weather
import com.igorgiroti.weathertracker.domain.usecase.GetSearchUseCase
import com.igorgiroti.weathertracker.domain.usecase.GetWeatherApiUseCase
import com.igorgiroti.weathertracker.utils.ResponseStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WeatherApiViewModel(
    private val getWeatherUseCase: GetWeatherApiUseCase,
    private val getSearchUseCase: GetSearchUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState: MutableStateFlow<ResponseStatus<Weather>> =
        MutableStateFlow(ResponseStatus.Loading)
    val uiState = _uiState

    fun getWeather(city: String) {
        viewModelScope.launch(dispatcher) {
            getWeatherUseCase.execute(city).collect { response ->
                when(response){
                    is ResponseStatus.Loading -> {
                        Log.d("Test", "Loading")
                    }
                    is ResponseStatus.Success -> {
                        //Do Success
                        Log.d("Test","Sucess")
                    }
                    is ResponseStatus.Error ->{
                        Log.d("Test", "Error")
                    }
                }
            }
        }
    }

    fun getSearch(city: String) {
        viewModelScope.launch(dispatcher) {
            getSearchUseCase.execute(city).collect { response ->
                when(response){
                    is ResponseStatus.Loading -> {
                        Log.d("Test", "Loading Search")
                    }
                    is ResponseStatus.Success -> {
                        //Do Success
                        Log.d("Test","Sucess Search")
                    }
                    is ResponseStatus.Error ->{
                        Log.d("Test", "Error Search")
                    }
                }
            }
        }
    }
}
