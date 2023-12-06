package com.tech.api.datasource

import com.tech.api.model.Satellite

interface InitialResponseDataSource {
    suspend fun getSatellites(): List<Satellite>
}