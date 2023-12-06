package com.tech.domain_impl.mapper

import com.tech.api.model.Satellite
import com.tech.domain.model.SatelliteDomainModel
import javax.inject.Inject

internal class InitialDataMapper @Inject constructor() {

    /**
     * Converts the data model object to a domain model object.
     * @param satellite Satellite object retrieved from the Data layer.
     * @return SatelliteDomainModel object.
     */
    private fun mapToDomainModel(satellite: Satellite): SatelliteDomainModel {
        return SatelliteDomainModel(
            id = satellite.id,
            active = satellite.active,
            name = satellite.name
        )
    }

    /**
     * Function to handle multiple Satellite objects.
     * @param satelliteList Satellite list retrieved from the data layer.
     * @return Domain model SatelliteDomainModel list.
     */
    fun mapToDomainModelList(satelliteList: List<Satellite>): List<SatelliteDomainModel> {
        return satelliteList.map { mapToDomainModel(it) }
    }

}