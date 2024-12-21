package com.igorgiroti.weathertracker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.igorgiroti.weathertracker.presentation.ui.screen.WeatherTrackerScreen
import com.igorgiroti.weathertracker.presentation.ui.theme.WeatherTrackerTheme
import com.igorgiroti.weathertracker.presentation.ui.viewModel.WeatherApiViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherTrackerActivity : ComponentActivity() {

    private val weatherViewModel: WeatherApiViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTrackerTheme {
                WeatherTrackerScreen(weatherViewModel)
            }
        }
    }
}

