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
import androidx.compose.ui.unit.dp
import com.igorgiroti.weathertracker.domain.model.Search
import com.igorgiroti.weathertracker.domain.model.Weather
import com.igorgiroti.weathertracker.presentation.ui.screen.layouts_components.InitialScreenLayout
import com.igorgiroti.weathertracker.presentation.ui.screen.layouts_components.LoadingScreenLayout
import com.igorgiroti.weathertracker.presentation.ui.screen.layouts_components.SearchBar
import com.igorgiroti.weathertracker.presentation.ui.screen.layouts_components.SearchResultLayout
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
    var search by remember { mutableStateOf(EMPTY) }
    var lastSearch by remember { mutableStateOf(EMPTY) }

    LaunchedEffect(search) {
        if (search.isEmpty()) {
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
                modifier = Modifier.padding(top = 44.dp),
                search = search,
                onSearchChange = { searchValue ->
                    search = searchValue
                }
            )
        }
    ) { padding ->

        Column(modifier = Modifier.padding(padding)) {
            when (uiState) {
                is WeatherUiState.Initial -> {
                    InitialScreenLayout()
                }

                is WeatherUiState.Loading -> {
                    LoadingScreenLayout()
                }

                is WeatherUiState.Success -> {
                    Text("Sucesso ${(uiState as WeatherUiState.Success<Weather?>).data?.location?.name}")
                }

                is WeatherUiState.Error -> {
                    Text("Error")
                }

                WeatherUiState.Searching -> {
                    when (searchState) {
                        is SearchState.Loading -> {
                            LoadingScreenLayout()
                        }

                        is SearchState.Error -> {
                            Text("Deu ruim a busca")
                        }

                        is SearchState.Success -> {
                            SearchResultLayout(
                                searchList = (searchState as SearchState.Success<List<Search>?>).data,
                                onClick = { city ->
                                    viewModel.getSearch(city)
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





