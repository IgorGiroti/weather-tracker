package com.igorgiroti.weathertracker.presentation.ui.screen.layouts_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.igorgiroti.weathertracker.R
import com.igorgiroti.weathertracker.presentation.ui.theme.NeutralGray

@Composable
fun EmptyScreenLayout() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 240.dp),
            color = NeutralGray,
            text = stringResource(R.string.no_city_title),
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            color = NeutralGray,
            text = stringResource(R.string.no_city_description),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.W600
            )
        )
    }
}
