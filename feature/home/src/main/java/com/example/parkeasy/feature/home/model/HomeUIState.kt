package com.example.parkeasy.feature.home.model

import com.example.parkeasy.core.domain.model.Location
import com.example.parkeasy.core.domain.model.ParkingLot

interface HomeUIState {

    object Loading : HomeUIState
    data class Success(
        val currentLocation: Location,
        val parkingLots: List<ParkingLot>,
        val dialogState: DialogState = DialogState(),
    ) : HomeUIState

    data class Error(
        val message: String,
        val exception: Throwable? = null,
    ) : HomeUIState
}