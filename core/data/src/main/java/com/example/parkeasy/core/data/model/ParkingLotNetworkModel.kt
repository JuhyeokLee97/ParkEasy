package com.example.parkeasy.core.data.model

data class ParkingLotNetworkModel(
    val id: Int,
    val name: String,
    val pricePerHour: Int,
    val address: String,
    val availableStartTime: String, // 07:00
    val availableEndTime: String, // 23:00
    val availablePlace: Int,
    val imageUrl: String,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
