package com.example.parkeasy.core.data.mapper

import com.example.parkeasy.core.data.model.ParkingLotNetworkModel
import com.example.parkeasy.core.domain.model.ParkingLot

fun ParkingLotNetworkModel.asDomain(): ParkingLot {
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
        longitude = longitude,
    )
}

fun List<ParkingLotNetworkModel>.asDomain(): List<ParkingLot> = map { it.asDomain() }