package com.example.parkeasy.feature.detail.domain.usecase

import com.example.parkeasy.feature.detail.data.ParkingLotDetailEntity

interface GetParkingLotUseCase {
    suspend operator fun invoke(id: Int): ParkingLotDetailEntity?
}