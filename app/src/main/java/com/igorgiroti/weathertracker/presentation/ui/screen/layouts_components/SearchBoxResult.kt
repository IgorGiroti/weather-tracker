package com.igorgiroti.weathertracker.presentation.ui.screen.layouts_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorgiroti.weathertracker.domain.model.Search
import com.igorgiroti.weathertracker.presentation.ui.theme.Gainsboro
import com.igorgiroti.weathertracker.presentation.ui.theme.NeutralGray

@Composable
fun SearchBoxResult(
    searchModel: Search,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Gainsboro,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 24.dp)
            .clickable {
                onClick(searchModel.name)
            }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                color = NeutralGray,
                text = searchModel.name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 20.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 5.dp, bottom = 8.dp),
                color = NeutralGray,
                text = "${searchModel.region},${searchModel.country} ",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}