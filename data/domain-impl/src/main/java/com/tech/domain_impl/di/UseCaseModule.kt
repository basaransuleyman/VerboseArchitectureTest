package com.tech.domain_impl.di

import com.tech.domain.usecase.GetSatellitesFromMemoryUseCase
import com.tech.domain.usecase.SatelliteUseCase
import com.tech.domain.usecase.UpdatePositionUseCase
import com.tech.domain_impl.usecase.GetSatellitesFromMemoryUseCaseImpl
import com.tech.domain_impl.usecase.SatelliteUseCaseImpl
import com.tech.domain_impl.usecase.UpdatePositionUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    internal abstract fun bindSatelliteUseCase(impl: SatelliteUseCaseImpl): SatelliteUseCase

    @Binds
    internal abstract fun bindSatelliteUpdatingPosition(impl: UpdatePositionUseCaseImpl): UpdatePositionUseCase

    @Binds
    internal abstract fun bindSatelliteDetailUseCase(impl: GetSatellitesFromMemoryUseCaseImpl): GetSatellitesFromMemoryUseCase
}
