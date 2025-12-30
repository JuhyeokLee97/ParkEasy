package com.example.domain.repository

import com.example.domain.model.Location

interface LocationRepository {

    suspend fun getCurrentLocation(): Location
}