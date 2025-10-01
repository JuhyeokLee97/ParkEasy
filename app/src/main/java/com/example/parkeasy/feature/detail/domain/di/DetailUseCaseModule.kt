package com.example.parkeasy.feature.detail.domain.di

import com.example.parkeasy.feature.detail.domain.usecase.GetParkingLotUseCase
import com.example.parkeasy.feature.detail.domain.usecase.GetParkingLotUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DetailUseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGetParkingLotUseCase(
        getParkingLotUseCase: GetParkingLotUseCaseImpl
    ): GetParkingLotUseCase
}