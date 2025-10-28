package com.example.parkeasy.core.data.repository

import android.annotation.SuppressLint
import android.content.Context
import com.example.parkeasy.core.domain.model.Location
import com.example.parkeasy.core.domain.model.Result
import com.example.parkeasy.core.domain.repository.LocationRepository
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FusedLocationRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : LocationRepository {

    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Result<Location> {
        return try {
            val location = fusedLocationClient.lastLocation.await()
            if (location != null) {
                Result.Success(
                    Location(
                        latitude = location.latitude,
                        longitude = location.longitude
                    )
                )
            } else {
                Result.Error(
                    exception = IllegalStateException("Location is null"),
                    message = "위치 정보를 가져올 수 없습니다."
                )
            }
        } catch (e: SecurityException) {
            Result.Error(
                exception = e,
                message = "위치 정보에 대한 권한이 없습니다."
            )
        } catch (e: Exception) {
            Result.Error(
                exception = e,
                message = "위치 정보를 가져오는 중 오류가 발생했습니다."
            )
        }

    }
}