package com.example.parkeasy.feature.around.domain.usecase

import com.example.parkeasy.feature.around.data.ParkingLotEntity

interface GetAroundParkingLotsUseCase {

    suspend operator fun invoke(): List<ParkingLotEntity>
}