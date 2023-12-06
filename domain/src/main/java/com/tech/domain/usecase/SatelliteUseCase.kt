package com.tech.domain.usecase

import com.tech.domain.model.SatelliteDomainModel
import kotlinx.coroutines.flow.Flow

interface SatelliteUseCase {
    fun getSatellites(): Flow<List<SatelliteDomainModel>>
}