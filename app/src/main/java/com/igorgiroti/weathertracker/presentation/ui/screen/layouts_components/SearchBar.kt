package com.igorgiroti.weathertracker.presentation.ui.screen.layouts_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.igorgiroti.weathertracker.presentation.ui.theme.Gainsboro
import com.igorgiroti.weathertracker.presentation.ui.theme.LightGray


@Composable
fun SearchBar(
    search: String,
    onSearchChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 24.dp)
    ) {
        TextField(
            value = search,
            onValueChange = onSearchChange,
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(
                    color = LightGray,
                    text = "Search Location",
                    style = MaterialTheme.typography.bodyMedium,
                )
            },
            singleLine = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = LightGray
                )
            },
            colors = TextFieldDefaults.colors().copy(
                focusedContainerColor = Gainsboro,
                unfocusedContainerColor = Gainsboro,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(16.dp),
        )
    }
}