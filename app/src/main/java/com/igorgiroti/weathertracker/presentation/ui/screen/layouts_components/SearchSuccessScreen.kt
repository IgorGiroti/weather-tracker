package com.igorgiroti.weathertracker.presentation.ui.screen.layouts_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.igorgiroti.weathertracker.domain.model.Search


@Composable
fun SearchResultLayout(
    searchList: List<Search>?,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier.padding(top = 32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        searchList?.forEach { searchModel ->
            item {
                SearchBoxResult(
                    searchModel = searchModel,
                    onClick = onClick,
                    modifier = modifier
                )
            }
        }
    }
}