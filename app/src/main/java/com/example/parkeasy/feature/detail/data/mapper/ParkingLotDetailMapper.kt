package com.example.parkeasy.feature.detail.data.mapper

import com.example.parkeasy.feature.detail.data.ParkingLotDetailEntity
import com.example.parkeasy.repository.data.ParkingLot

fun ParkingLot.toParkingLotDetailEntity() = ParkingLotDetailEntity(
    id = id,
    name = name,
    pricePerHour = pricePerHour,
    address = address,
    availableTime = "$availableStartTime ~ $availableEndTime",
    availablePlace = availablePlace
)