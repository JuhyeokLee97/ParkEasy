package com.example.data.model

import com.example.domain.model.ParkingLot
import kotlinx.serialization.Serializable

@Serializable
data class ParkingLotDto(
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

fun ParkingLotDto.toDomain(): ParkingLot {
    return ParkingLot(
        id = id,
        name = name,
        pricePerHour = pricePerHour,
        address = address,
        availableStartTime = availableStartTime,
        availableEndTime = availableEndTime,
        availablePlace = availablePlace,
        imageUrl = imageUrl,
        latitude = latitude,
        longitude = longitude
    )
}