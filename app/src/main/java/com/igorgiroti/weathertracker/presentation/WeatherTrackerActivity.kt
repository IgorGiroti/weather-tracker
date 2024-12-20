package com.igorgiroti.weathertracker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.igorgiroti.weathertracker.presentation.ui.theme.WeatherTrackerTheme
import com.igorgiroti.weathertracker.presentation.ui.viewModel.WeatherApiViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherTrackerActivity : ComponentActivity() {

    private val weatherViewModel: WeatherApiViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Button(
                        onClick = {
                            weatherViewModel.getWeather("London")
                        }
                    ) {}
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherTrackerTheme {
        Greeting("Android")
    }
}
