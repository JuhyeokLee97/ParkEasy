package com.example.data.di

import com.example.data.datasource.FusedLocationDataSource
import com.example.data.datasource.LocationDataSource
import com.example.data.datasource.ParkingLotDataSource
import com.example.data.datasource.ParkingLotFakeDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindLocationDataSource(impl: FusedLocationDataSource): LocationDataSource

    @Binds
    fun bindParkingLotDataSource(impl: ParkingLotFakeDataSource): ParkingLotDataSource
}