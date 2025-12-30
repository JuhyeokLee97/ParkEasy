package com.example.data.datasource

import com.example.data.model.ParkingLotDto

interface ParkingLotDataSource {

    suspend fun getParkingLots(latitude: Double, longitude: Double): List<ParkingLotDto>
}