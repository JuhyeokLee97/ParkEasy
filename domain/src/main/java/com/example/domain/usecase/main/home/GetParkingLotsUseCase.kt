package com.example.domain.usecase.main.home

import com.example.domain.model.ParkingLot
import com.example.domain.repository.ParkingLotRepository
import javax.inject.Inject

class GetParkingLotsUseCase @Inject constructor(
    private val parkingLotRepository: ParkingLotRepository
) {

    suspend operator fun invoke(latitude: Double, longitude: Double): Result<List<ParkingLot>> {
        return try {
            val parkingLots = parkingLotRepository.getParkingLots(latitude, longitude)
            Result.success(parkingLots)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}