package com.example.parkeasy.feature.detail.data

sealed interface ParkingLotState {
    object Loading : ParkingLotState
    data class Success(val parkingLot: ParkingLotDetailEntity) : ParkingLotState
    data class Error(val message: String) : ParkingLotState
}

data class DetailUiState(
    val showServicePreparingDialog: Boolean = false,
    val parkingLotState: ParkingLotState = ParkingLotState.Loading
)