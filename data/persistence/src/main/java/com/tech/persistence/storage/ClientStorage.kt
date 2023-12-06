package com.tech.persistence.storage

import com.tech.domain.model.Position
import com.tech.persistence.SatelliteDetailResponse

interface ClientStorage {
   suspend fun getSatelliteDetail(id: Int): SatelliteDetailResponse?

   suspend fun getPositionsForSatellite(id: Int): List<Position>
}