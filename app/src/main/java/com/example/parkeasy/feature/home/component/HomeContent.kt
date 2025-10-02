package com.example.parkeasy.feature.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.parkeasy.R
import com.example.parkeasy.feature.around.data.ParkingLotEntity
import com.example.parkeasy.ui.theme.Paddings
import com.google.android.gms.maps.model.LatLng

@Composable
fun HomeContent(
    currentLocation: LatLng?,
    isLocationLoading: Boolean,
    nearbyParkingLots: List<ParkingLotEntity>,
    onNavigateToParkEasy: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MapSection(
            currentLocation = currentLocation,
            isLocationLoading = isLocationLoading,
            nearbyParkingLots = nearbyParkingLots,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        ButtonSection(
            onNavigateToParkEasy = onNavigateToParkEasy,
            onFavoriteClick = onFavoriteClick
        )
    }
}

@Composable
fun ButtonSection(
    onNavigateToParkEasy: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .padding(
                vertical = Paddings.large,
                horizontal = Paddings.medium
            )
            .fillMaxWidth()
    ) {
        Button(
            onClick = onNavigateToParkEasy,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "주변 주차장 찾기",
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.width(Paddings.large))
        Button(
            onClick = onFavoriteClick,
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = stringResource(R.string.favorite),
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
        }
    }

}