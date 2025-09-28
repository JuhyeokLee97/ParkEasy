package com.example.parkeasy.feature.arround.data

data class ParkingLotEntity(
    val id: Int,
    val name: String,
    val pricePerHour: Int,
    val address: String,
    val availableTime: String,
    val availablePlace: Int
)
