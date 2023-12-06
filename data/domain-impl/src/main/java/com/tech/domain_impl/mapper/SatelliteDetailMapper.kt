package com.tech.domain_impl.mapper

import com.tech.domain.model.SatelliteDetailDomainModel
import com.tech.persistence.SatelliteDetailResponse
fun SatelliteDetailResponse.toDomainModel(): SatelliteDetailDomainModel {
    return SatelliteDetailDomainModel(
        id = this.id,
        costPerLaunch = this.costPerLaunch,
        firstFlight = this.firstFlight ?: "N/A",
        height = this.height,
        mass = this.mass
    )
}