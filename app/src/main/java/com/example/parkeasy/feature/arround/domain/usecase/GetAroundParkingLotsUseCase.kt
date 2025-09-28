package com.example.parkeasy.feature.arround.domain.usecase

import com.example.parkeasy.feature.arround.data.ParkingLotEntity

interface GetAroundParkingLotsUseCase {

    suspend operator fun invoke(): List<ParkingLotEntity>
}