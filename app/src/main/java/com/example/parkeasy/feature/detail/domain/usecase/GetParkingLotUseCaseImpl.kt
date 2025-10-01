package com.example.parkeasy.feature.detail.domain.usecase

import com.example.parkeasy.feature.detail.data.ParkingLotDetailEntity
import com.example.parkeasy.feature.detail.data.mapper.toParkingLotDetailEntity
import com.example.parkeasy.repository.ParkRepository
import javax.inject.Inject

class GetParkingLotUseCaseImpl @Inject constructor(
    val parkRepository: ParkRepository
) : GetParkingLotUseCase {

    override suspend fun invoke(id: Int): ParkingLotDetailEntity? {
        return parkRepository.getParkingLot(id)?.toParkingLotDetailEntity() ?: null
    }
}