package com.example.parkeasy.repository

import com.example.parkeasy.repository.data.ParkingLot

interface ParkRepository {

    suspend fun getParkingLots(): List<ParkingLot>
    suspend fun getParkingLot(id: Int): ParkingLot?
    suspend fun getParkingLotNearby(
        latitude: Double,
        longitude: Double,
        radiusInKm: Double = 5.0
    ): List<ParkingLot>
}