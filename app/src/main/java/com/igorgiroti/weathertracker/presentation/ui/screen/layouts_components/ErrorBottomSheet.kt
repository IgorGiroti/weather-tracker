package com.igorgiroti.weathertracker.presentation.ui.screen.layouts_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.igorgiroti.weathertracker.R
import com.igorgiroti.weathertracker.presentation.ui.theme.NeutralGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
        content = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    color = NeutralGray,
                    text = stringResource(R.string.error),
                    style = MaterialTheme.typography.bodyMedium
                )
                Button(onClick = onDismiss) {
                    Text(
                        text = stringResource(R.string.close),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    )

}