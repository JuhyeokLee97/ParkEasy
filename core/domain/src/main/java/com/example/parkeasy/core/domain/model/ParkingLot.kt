package com.example.parkeasy.core.domain.model

data class ParkingLot(
    val id: Int,
    val name: String,
    val pricePerHour: Int,
    val address: String,
    val availableTime: String,
    val availablePlace: Int
)
