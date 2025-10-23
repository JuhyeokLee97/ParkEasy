package com.example.parkeasy.core.domain.repository

import com.example.parkeasy.core.domain.model.ParkingLot
import com.example.parkeasy.core.domain.model.Result

interface ParkingLotRepository {

    suspend fun getParkingLots(): Result<List<ParkingLot>>
    suspend fun getParkingLot(id: Int): Result<ParkingLot>
    suspend fun getParkingLotNearby(
        latitude: Double,
        longitude: Double,
        radiusInKm: Double
    ): Result<List<ParkingLot>>
}