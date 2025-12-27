package com.example.domain.usecase.main.home

import com.example.domain.model.Location
import com.example.domain.repository.LocationRepository
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {

    suspend operator fun invoke(): Result<Location> {
        return try {
            Result.success(locationRepository.getCurrentLocation())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}