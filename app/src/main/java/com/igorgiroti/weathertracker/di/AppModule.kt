package com.igorgiroti.weathertracker.di

import com.igorgiroti.weathertracker.data.repository.WeatherApiRepositoryImpl
import com.igorgiroti.weathertracker.domain.repository.WeatherApiRepository
import com.igorgiroti.weathertracker.domain.usecase.GetWeatherApiUseCase
import com.igorgiroti.weathertracker.presentation.ui.viewModel.WeatherApiViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val appModule = module {
        single<WeatherApiRepository> {
            WeatherApiRepositoryImpl(get())
        }

        factory {
            GetWeatherApiUseCase(weatherRepository = get())
        }

        viewModel {
            WeatherApiViewModel(
                getWeatherUseCase = get()
            )
        }
    }
}
