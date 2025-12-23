package com.example.data.di

import com.example.data.repository.ParkingLotMockRepositoryImpl
import com.example.domain.repository.ParkingLotRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindParkingLotRepository(impl: ParkingLotMockRepositoryImpl): ParkingLotRepository
}