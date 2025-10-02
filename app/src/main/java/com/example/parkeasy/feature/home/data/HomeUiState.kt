package com.example.parkeasy.feature.home.data

import com.example.parkeasy.feature.around.data.ParkingLotEntity
import com.google.android.gms.maps.model.LatLng

data class HomeUiState(
    val showServicePreparingDialog: Boolean = false,
    val currentLocation: LatLng? = null,
    val isLocationLoading: Boolean = true,
    val nearbyParkingLots: List<ParkingLotEntity> = emptyList(),
)