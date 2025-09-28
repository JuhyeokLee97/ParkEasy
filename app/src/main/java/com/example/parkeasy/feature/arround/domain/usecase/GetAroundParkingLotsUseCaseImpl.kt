package com.example.parkeasy.feature.arround.domain.usecase

import com.example.parkeasy.feature.arround.data.ParkingLotEntity
import com.example.parkeasy.feature.arround.data.mapper.toParkingLotEntity
import com.example.parkeasy.repository.ParkRepository
import javax.inject.Inject

class GetAroundParkingLotsUseCaseImpl @Inject constructor(
    private val parkRepository: ParkRepository
) : GetAroundParkingLotsUseCase {

    override suspend fun invoke(): List<ParkingLotEntity> {
        return parkRepository.getParkingLots().map {
            it.toParkingLotEntity()
        }
    }
}