package com.example.parkeasy.core.domain.repository

import com.example.parkeasy.core.domain.model.Location
import com.example.parkeasy.core.domain.model.Result

interface LocationRepository {
    suspend fun getCurrentLocation(): Result<Location>
}