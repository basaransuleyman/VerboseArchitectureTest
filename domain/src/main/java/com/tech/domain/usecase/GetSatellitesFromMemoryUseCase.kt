package com.tech.domain.usecase

import com.tech.domain.model.SatelliteDetailDomainModel
import kotlinx.coroutines.flow.Flow

interface GetSatellitesFromMemoryUseCase {
    fun getSatellitesFromMemory(id: Int): Flow<SatelliteDetailDomainModel>
}