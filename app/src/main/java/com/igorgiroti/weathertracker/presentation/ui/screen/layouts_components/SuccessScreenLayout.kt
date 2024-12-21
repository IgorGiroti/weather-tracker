package com.igorgiroti.weathertracker.presentation.ui.screen.layouts_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.igorgiroti.weathertracker.R
import com.igorgiroti.weathertracker.domain.model.Weather
import com.igorgiroti.weathertracker.presentation.ui.theme.Gainsboro
import com.igorgiroti.weathertracker.presentation.ui.theme.Gray
import com.igorgiroti.weathertracker.presentation.ui.theme.LightGray
import com.igorgiroti.weathertracker.presentation.ui.theme.NeutralGray
import com.igorgiroti.weathertracker.utils.toDegree
import com.igorgiroti.weathertracker.utils.toHttps
import com.igorgiroti.weathertracker.utils.toPercentage

@Composable
fun SuccessScreenLayout(
    weatherModel: Weather,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = modifier
                .size(150.dp)
                .padding(top = 80.dp),
            model = weatherModel.weather.condition.iconUrl.toHttps(),
            contentDescription = null
        )
        Row(modifier = Modifier.padding(top = 24.dp)) {
            Text(
                color = NeutralGray,
                text = weatherModel.location.name,
                style = MaterialTheme.typography.bodyLarge,
            )

            Image(
                modifier = Modifier
                    .size(21.dp)
                    .padding(start = 8.dp),
                painter = painterResource(id = R.drawable.direction_icon),
                contentDescription = null
            )

        }
        Text(
            modifier = Modifier.padding(top = 24.dp),
            color = NeutralGray,
            text = weatherModel.weather.temp.toDegree(),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 70.sp,
                fontWeight = FontWeight.W500,
                lineHeight = 105.sp
            ),
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 32.dp)
                .background(
                    color = Gainsboro,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Row(
                Modifier
                    .fillMaxWidth()

            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        color = LightGray,
                        text = stringResource(R.string.humidity),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        modifier = Modifier.padding(top = 5.dp, bottom = 8.dp),
                        color = Gray,
                        text = weatherModel.weather.humidity.toString().toPercentage(),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W500
                        ),
                    )
                }
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        color = LightGray,
                        text = stringResource(R.string.uv),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        modifier = Modifier.padding(top = 5.dp, bottom = 8.dp),
                        color = Gray,
                        text = weatherModel.weather.uv.toInt().toString(),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W500
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        color = LightGray,
                        text = stringResource(R.string.feels_like),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        modifier = Modifier.padding(top = 5.dp, bottom = 8.dp),
                        color = Gray,
                        text = weatherModel.weather.feelsLike.toDegree(),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W500
                        )
                    )
                }
            }
        }
    }
}
