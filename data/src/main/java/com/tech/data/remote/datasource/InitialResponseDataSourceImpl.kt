package com.tech.data.remote.datasource

import com.tech.data.extensions.handleCall
import com.tech.data.model.Satellite
import com.tech.data.remote.Api
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