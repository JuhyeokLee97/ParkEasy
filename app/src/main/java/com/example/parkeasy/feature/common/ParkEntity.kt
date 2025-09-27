package com.example.parkeasy.feature.common

data class ParkEntity(
    val id: Int,
    val name: String,
    val pricePerHour: Int,
    val address: String,
    val availableTime: String,
    val availablePlace: Int
)
