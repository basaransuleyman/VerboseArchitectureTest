package com.tech.data.remote.datasource

import com.tech.data.model.Satellite

internal interface InitialResponseDataSource {
    suspend fun getSatellites(): List<Satellite>
}