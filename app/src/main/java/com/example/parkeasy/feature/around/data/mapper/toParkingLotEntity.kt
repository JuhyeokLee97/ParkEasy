package com.example.parkeasy.feature.around.data.mapper

import com.example.parkeasy.feature.around.data.ParkingLotEntity
import com.example.parkeasy.repository.data.ParkingLot

fun ParkingLot.toParkingLotEntity(): ParkingLotEntity {
    return ParkingLotEntity(
        id = id,
        name = name,
        pricePerHour = pricePerHour,
        address = address,
        availableTime = "$availableStartTime ~ $availableEndTime",
        availablePlace = availablePlace,
        latitude = latitude,  // 추가
        longitude = longitude  // 추가
    )
}