package com.tech.domain_impl.mapper

import com.tech.api.model.Satellite
import com.tech.domain.model.SatelliteDomainModel

fun Satellite.toDomainModel(): SatelliteDomainModel {
    return SatelliteDomainModel(
        id = this.id,
        active = this.active,
        name = this.name
    )
}

fun List<Satellite>.toDomainModelList(): List<SatelliteDomainModel> {
    return this.map { it.toDomainModel() }
}