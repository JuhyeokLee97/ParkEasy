package com.example.parkeasy.repository.di

import com.example.parkeasy.repository.ParkRepository
import com.example.parkeasy.repository.ParkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindParkRepository(impl: ParkRepositoryImpl): ParkRepository
}