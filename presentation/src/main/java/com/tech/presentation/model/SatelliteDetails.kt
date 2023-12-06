package com.tech.presentation.model

data class SatelliteDetails(
    val id: Int,
    val costPerLaunch: Long,
    val firstFlight: String,
    val height: Int,
    val mass: Int
)