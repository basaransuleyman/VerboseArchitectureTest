package com.tech.data.di

import com.tech.data.usecase.SatelliteUseCaseImpl
import com.tech.domain.usecase.SatelliteUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    internal abstract fun bindSatelliteUseCase(impl: SatelliteUseCaseImpl): SatelliteUseCase
}
