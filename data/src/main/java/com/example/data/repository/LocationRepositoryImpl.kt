package com.example.data.repository

import com.example.data.datasource.LocationDataSource
import com.example.domain.model.Location
import com.example.domain.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationDataSource: LocationDataSource,
) : LocationRepository {


    override suspend fun getCurrentLocation(): Location {
        return locationDataSource.getCurrentLocation() ?: throw NullPointerException("Location is null")
    }
}