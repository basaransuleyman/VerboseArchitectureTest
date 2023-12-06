package com.tech.api.datasource

import com.tech.api.Api
import com.tech.api.extensions.handleCall
import com.tech.api.model.Satellite
import javax.inject.Inject

internal class InitialResponseDataSourceImpl @Inject constructor(
    private val api: Api
) : InitialResponseDataSource {
    override suspend fun getSatellites(): List<Satellite> {
        return handleCall {
            api.getInitialData()
        }
    }
}