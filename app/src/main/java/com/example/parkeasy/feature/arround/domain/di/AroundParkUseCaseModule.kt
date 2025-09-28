package com.example.parkeasy.feature.arround.domain.di

import com.example.parkeasy.feature.arround.domain.usecase.GetAroundParkingLotsUseCase
import com.example.parkeasy.feature.arround.domain.usecase.GetAroundParkingLotsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AroundParkUseCaseModule {

    @Singleton
    @Binds
    abstract fun bindGetAroundParkingLotsUseCase(
        getAroundParkingLotsUseCase: GetAroundParkingLotsUseCaseImpl
    ): GetAroundParkingLotsUseCase
}