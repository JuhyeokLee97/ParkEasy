package com.example.parkeasy.repository

import com.example.parkeasy.repository.data.ParkingLot
import com.example.parkeasy.repository.data.mockParkingLots
import javax.inject.Inject

class ParkRepositoryImpl @Inject constructor() : ParkRepository {

    override suspend fun getParkingLots(): List<ParkingLot> {
        return mockParkingLots
    }

    override suspend fun getParkingLot(id: Int): ParkingLot? {
        return mockParkingLots.find { it.id == id }
    }
}