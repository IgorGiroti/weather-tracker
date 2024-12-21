package com.igorgiroti.weathertracker.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.igorgiroti.weathertracker.domain.model.Search
import com.igorgiroti.weathertracker.domain.model.Weather
import com.igorgiroti.weathertracker.presentation.ui.screen.layouts_components.EmptyScreenLayout
import com.igorgiroti.weathertracker.presentation.ui.screen.layouts_components.LoadingScreenLayout
import com.igorgiroti.weathertracker.presentation.ui.screen.layouts_components.SearchBar
import com.igorgiroti.weathertracker.presentation.ui.screen.layouts_components.SearchResultLayout
import com.igorgiroti.weathertracker.presentation.ui.screen.layouts_components.SuccessScreenLayout
import com.igorgiroti.weathertracker.presentation.ui.state.SearchState
import com.igorgiroti.weathertracker.presentation.ui.state.WeatherUiState
import com.igorgiroti.weathertracker.presentation.ui.viewModel.WeatherApiViewModel
import com.igorgiroti.weathertracker.utils.DELAY
import com.igorgiroti.weathertracker.utils.EMPTY

@Composable
fun WeatherTrackerScreen(
    viewModel: WeatherApiViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchState by viewModel.searchState.collectAsState()
    val focusManager = LocalFocusManager.current
    var search by remember { mutableStateOf(EMPTY) }
    var isFocused by remember { mutableStateOf(false) }
    var lastSearch by remember { mutableStateOf(EMPTY) }


    LaunchedEffect(search) {
        if (search.isEmpty() && isFocused) {
            lastSearch = EMPTY
            viewModel.cleanSearchAndRestoreState()
        }

        kotlinx.coroutines.delay(DELAY)
        if (search.isNotBlank() && lastSearch != search) {
            lastSearch = search
            viewModel.getSearch(search)
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            SearchBar(
                modifier = Modifier
                    .padding(top = 44.dp)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                search = search,
                onSearchChange = { searchValue ->
                    search = searchValue
                }
            )
        }
    ) { padding ->

        Column(modifier = Modifier.padding(padding)) {
            when (uiState) {
                is WeatherUiState.Loading -> {
                    LoadingScreenLayout()
                }

                is WeatherUiState.Success -> {
                    val data = (uiState as WeatherUiState.Success<Weather?>).data

                    data?.let {
                        SuccessScreenLayout(weatherModel = data)
                    } ?: EmptyScreenLayout()

                }

                is WeatherUiState.Error -> {
                    Text("Error")
                }

                is WeatherUiState.Searching -> {
                    when (searchState) {
                        is SearchState.Loading -> {
                            LoadingScreenLayout()
                        }

                        is SearchState.Error -> {
                            Text("Error")
                        }

                        is SearchState.Success -> {
                            SearchResultLayout(
                                searchList = (searchState as SearchState.Success<List<Search>?>).data,
                                onClick = { city ->
                                    viewModel.getWeather(city)
                                    focusManager.clearFocus()
                                    search = EMPTY
                                }
                            )
                        }

                        SearchState.Initial -> Unit
                    }
                }
            }
        }
    }
}





