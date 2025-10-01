package com.example.parkeasy.feature.detail.data

data class ParkingLotDetailEntity(
    val id: Int,
    val name: String,
    val pricePerHour: Int,
    val address: String,
    val availableTime: String,
    val availablePlace: Int
)