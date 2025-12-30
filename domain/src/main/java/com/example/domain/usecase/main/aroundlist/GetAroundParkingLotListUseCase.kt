package com.example.domain.usecase.main.aroundlist

import com.example.domain.model.ParkingLot
import com.example.domain.repository.LocationRepository
import com.example.domain.repository.ParkingLotRepository
import javax.inject.Inject

class GetAroundParkingLotListUseCase @Inject constructor(
    private val locationRepository: LocationRepository,
    private val parkingLotRepository: ParkingLotRepository,
) {

    suspend operator fun invoke(): Result<List<ParkingLot>> {
        return try {
            val currentLocation = locationRepository.getCurrentLocation()
            val parkingLots = parkingLotRepository.getParkingLots(
                latitude = currentLocation.latitude,
                longitude = currentLocation.longitude
            )
            Result.success(parkingLots)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}