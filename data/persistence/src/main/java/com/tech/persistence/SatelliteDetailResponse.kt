package com.tech.persistence

import com.google.gson.annotations.SerializedName

data class SatelliteDetailResponse(
    val id: Int,
    val height: Int,
    val mass: Int,
    @SerializedName("cost_per_launch") val costPerLaunch: Int,
    @SerializedName("first_flight") val firstFlight: String?,
)