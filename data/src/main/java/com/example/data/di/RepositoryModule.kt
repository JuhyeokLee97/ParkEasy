package com.example.data.di

import com.example.data.repository.ParkingLotMockRepositoryImpl
import com.example.data.repository.login.AuthRepositoryImpl
import com.example.domain.repository.ParkingLotRepository
import com.example.domain.repository.login.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindParkingLotRepository(impl: ParkingLotMockRepositoryImpl): ParkingLotRepository

    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}