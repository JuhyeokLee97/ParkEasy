package com.example.parkeasy.feature.arround.data.mapper

import com.example.parkeasy.feature.arround.data.ParkingLotEntity
import com.example.parkeasy.repository.data.ParkingLot

fun ParkingLot.toParkingLotEntity(): ParkingLotEntity {
    return ParkingLotEntity(
        id = id,
        name = name,
        pricePerHour = pricePerHour,
        address = address,
        availableTime = "$availableStartTime ~ $availableEndTime",
        availablePlace = availablePlace
    )
}