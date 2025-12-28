package com.example.data.repository

import com.example.data.datasource.ParkingLotDataSource
import com.example.data.model.toDomain
import com.example.domain.model.ParkingLot
import com.example.domain.repository.ParkingLotRepository
import javax.inject.Inject

class ParkingLotMockRepositoryImpl @Inject constructor(
    private val parkingLotDataSource: ParkingLotDataSource
) : ParkingLotRepository {

    override suspend fun getParkingLots(latitude: Double, longitude: Double): List<ParkingLot> {
        return parkingLotDataSource.getParkingLots(latitude, longitude).map { parkingLotDto ->
             parkingLotDto.toDomain()
        }
    }
}