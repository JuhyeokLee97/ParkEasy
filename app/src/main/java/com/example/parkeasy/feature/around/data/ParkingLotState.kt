package com.example.parkeasy.feature.around.data

sealed class ParkingLotState {
    object Loading : ParkingLotState()
    data class Loaded(
        val parkingLots: List<ParkingLotEntity>
    ) : ParkingLotState()
}