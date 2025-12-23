package com.example.domain.usecase

import com.example.domain.model.ParkingLot
import com.example.domain.repository.ParkingLotRepository
import javax.inject.Inject

class GetParkingLotsUseCase @Inject constructor(
    private val parkingLotRepository: ParkingLotRepository
) {

    suspend operator fun invoke(latitude: Double, longitude: Double): Result<List<ParkingLot>> {
        return parkingLotRepository.getParkingLots(latitude, longitude)
    }
}