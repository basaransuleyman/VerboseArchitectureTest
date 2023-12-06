package com.tech.presentation.mapper

import com.tech.domain.model.SatelliteDomainModel
import com.tech.presentation.model.SatelliteUIModel

fun List<SatelliteDomainModel>.toUiModelList(): List<SatelliteUIModel> {
    return this.map { domainModel ->
        SatelliteUIModel(
            id = domainModel.id,
            active = domainModel.active,
            name = domainModel.name
        )
    }
}