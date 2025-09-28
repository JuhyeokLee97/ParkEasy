package com.example.parkeasy.repository

import com.example.parkeasy.repository.data.ParkingLot

interface ParkRepository {

    suspend fun getParkingLots(): List<ParkingLot>
}