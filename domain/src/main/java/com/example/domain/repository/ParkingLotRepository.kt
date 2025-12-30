package com.example.domain.repository

import com.example.domain.model.ParkingLot

interface ParkingLotRepository {

    suspend fun getParkingLots(latitude: Double, longitude: Double): List<ParkingLot>
}