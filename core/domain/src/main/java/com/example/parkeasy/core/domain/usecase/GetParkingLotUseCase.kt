package com.example.parkeasy.core.domain.usecase

import com.example.parkeasy.core.domain.model.ParkingLot
import com.example.parkeasy.core.domain.model.Result
import com.example.parkeasy.core.domain.repository.ParkingLotRepository
import javax.inject.Inject

class GetParkingLotUseCase @Inject constructor(
    private val parkingLotRepository: ParkingLotRepository
) {

    suspend operator fun invoke(id: Int): Result<ParkingLot> {
        return when(val result = parkingLotRepository.getParkingLot(id)) {
            is Result.Success -> Result.Success(result.data)
            is Result.Error -> Result.Error(result.exception, result.message)
            is Result.Loading -> Result.Loading
        }
    }
}