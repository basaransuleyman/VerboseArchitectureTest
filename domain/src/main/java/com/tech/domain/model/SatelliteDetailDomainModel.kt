package com.tech.domain.model

data class SatelliteDetailDomainModel(
    val id: Int,
    val costPerLaunch: Int,
    val firstFlight: String,
    val height: Int,
    val mass: Int
)