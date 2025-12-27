package com.example.data.datasource

import com.example.domain.model.Location

interface LocationDataSource {

    suspend fun getCurrentLocation(): Location?
}