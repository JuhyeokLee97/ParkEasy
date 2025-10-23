package com.example.parkeasy.core.data.di

import com.example.parkeasy.core.data.repository.ParkingLotRepositoryImpl
import com.example.parkeasy.core.domain.repository.ParkingLotRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindParkingLotRepository(
        impl: ParkingLotRepositoryImpl
    ): ParkingLotRepository
}