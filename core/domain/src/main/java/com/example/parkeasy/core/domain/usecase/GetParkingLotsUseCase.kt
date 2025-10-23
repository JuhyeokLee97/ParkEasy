package com.example.parkeasy.core.domain.usecase

import com.example.parkeasy.core.domain.model.ParkingLot
import com.example.parkeasy.core.domain.repository.ParkingLotRepository
import com.example.parkeasy.core.domain.model.Result
import javax.inject.Inject

class GetParkingLotsUseCase @Inject constructor(
    private val parkingLotRepository: ParkingLotRepository
) {

    suspend operator fun invoke(): Result<List<ParkingLot>> {
        return when (val result = parkingLotRepository.getParkingLots()) {
            is Result.Success -> Result.Success(result.data)
            is Result.Error -> Result.Error(result.exception, result.message)
            is Result.Loading -> Result.Loading
        }
    }
}