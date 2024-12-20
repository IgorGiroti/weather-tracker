package com.igorgiroti.weathertracker.di

import com.igorgiroti.weathertracker.data.repository.WeatherApiRepositoryImpl
import com.igorgiroti.weathertracker.domain.repository.WeatherApiRepository
import com.igorgiroti.weathertracker.domain.usecase.GetSearchUseCase
import com.igorgiroti.weathertracker.domain.usecase.GetWeatherApiUseCase
import com.igorgiroti.weathertracker.presentation.ui.viewModel.WeatherApiViewModel
import kotlinx.coroutines.Dispatchers
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

        factory {
            GetSearchUseCase(weatherRepository = get())
        }

        viewModel {
            WeatherApiViewModel(
                getWeatherUseCase = get(),
                getSearchUseCase = get(),
                dispatcher = Dispatchers.IO
            )
        }
    }
}
