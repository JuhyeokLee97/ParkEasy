package com.example.data.datasource

import android.content.Context
import com.example.domain.model.Location
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FusedLocationDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) : LocationDataSource {

    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    override suspend fun getCurrentLocation(): Location? {
        return fusedLocationClient.lastLocation.await()?.let { fusedLocation ->
            Location(
                latitude = fusedLocation.latitude,
                longitude = fusedLocation.longitude
            )
        }
    }
}