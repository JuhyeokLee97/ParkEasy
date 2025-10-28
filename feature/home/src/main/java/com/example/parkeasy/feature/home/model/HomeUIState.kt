package com.example.parkeasy.feature.home.model

import com.example.parkeasy.core.domain.model.ParkingLot
import com.google.android.gms.maps.model.LatLng

interface HomeUIState {

    object Loading : HomeUIState
    data class Success(
        val currentLocation: LatLng? = null,
        val parkingLots: List<ParkingLot>,
        val dialogState: DialogState = DialogState(),
    ) : HomeUIState

    data class Error(
        val message: String,
        val exception: Throwable? = null,
    ) : HomeUIState
}