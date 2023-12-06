package com.tech.domain.model

data class SatellitePosition(
    val id: String,
    val positions: List<Position>
)

data class Position(
    val posX: Double,
    val posY: Double
)