package com.example.parkeasy.repository

import com.example.parkeasy.repository.data.ParkingLot
import com.example.parkeasy.repository.data.generateParkingLotsNearby
import com.example.parkeasy.repository.data.mockParkingLots
import javax.inject.Inject

class ParkRepositoryImpl @Inject constructor() : ParkRepository {

    override suspend fun getParkingLots(): List<ParkingLot> {
        // 기본: 서울시청 기준
        return generateParkingLotsNearby(
            centerLat = 37.566535,
            centerLng = 126.9779692,
            radiusInKm = 5.0
        )

    }

    override suspend fun getParkingLot(id: Int): ParkingLot? {
        return mockParkingLots.find { it.id == id }
    }

    override suspend fun getParkingLotNearby(latitude: Double, longitude: Double, radiusInKm: Double): List<ParkingLot> {
        return generateParkingLotsNearby(
            centerLat = latitude,
            centerLng = longitude,
            radiusInKm = radiusInKm
        )
    }
}