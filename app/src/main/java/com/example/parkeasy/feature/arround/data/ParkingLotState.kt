package com.example.parkeasy.feature.arround.data

sealed class ParkingLotState {
    object Loading : ParkingLotState()
    data class Loaded(
        val parkingLots: List<ParkingLotEntity>
    ) : ParkingLotState()
}