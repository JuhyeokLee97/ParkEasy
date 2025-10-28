package com.example.parkeasy.feature.home.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.parkeasy.core.domain.model.ParkingLot
import com.example.parkeasy.core.ui.components.button.PrimaryButton
import com.example.parkeasy.core.ui.components.button.SecondaryButton
import com.example.parkeasy.core.ui.util.Paddings
import com.example.parkeasy.feature.home.model.HomeUIState
import com.example.parkeasy.feature.home.util.toLatLng
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
internal fun HomeSuccessState(
    modifier: Modifier = Modifier,
    uiState: HomeUIState.Success,
    onNavigateToAroundParkingLot: () -> Unit,
    onFavoriteClick: () -> Unit,
) {
    // TODO implement dialog visible/invisible
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ParkingLotMap(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            currentLocation = uiState.currentLocation.toLatLng(),
            parkingLots = uiState.parkingLots
        )
        Row(
            modifier = Modifier
                .padding(
                    vertical = Paddings.large,
                    horizontal = Paddings.medium
                )
                .fillMaxWidth()
        ) {
            FindAroundParkingLotButton { onNavigateToAroundParkingLot() }

            Spacer(modifier = Modifier.width(Paddings.large))

            FavoriteButton { onFavoriteClick() }
        }
    }
}

@Composable
private fun ParkingLotMap(
    modifier: Modifier = Modifier,
    currentLocation: LatLng?,
    parkingLots: List<ParkingLot>,
) {
    val defaultLocation = LatLng(37.566535, 126.9779)
    val mapLocation = currentLocation ?: defaultLocation
    val zoom = 15f

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(mapLocation, zoom)
    }

    LaunchedEffect(currentLocation) {
        currentLocation?.let {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(it, zoom)
        }
    }

    Box(modifier = modifier) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = currentLocation != null
            ),
            uiSettings = MapUiSettings(
                myLocationButtonEnabled = true,
                zoomControlsEnabled = true
            ),
        ) {
            parkingLots.forEach { parkingLot ->
                Marker(
                    state = MarkerState(position = parkingLot.toLatLng()),
                    title = parkingLot.name,
                    snippet = "남은 자리 ${parkingLot.availablePlace}",
                )
            }
        }
    }
}

@Composable
private fun RowScope.FindAroundParkingLotButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    PrimaryButton(
        modifier = modifier.weight(1f),
        text = "주변 주차장 찾기",
        onClick = onClick
    )
}

@Composable
private fun RowScope.FavoriteButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    SecondaryButton(
        modifier = modifier.weight(1f),
        text = "즐겨찾기",
        onClick = onClick
    )
}