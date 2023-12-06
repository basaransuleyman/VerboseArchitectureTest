package com.tech.domain_impl.usecase

import com.tech.core.utils.IODispatcher
import com.tech.domain.model.SatelliteDomainModel
import com.tech.domain.usecase.SatelliteUseCase
import kotlinx.coroutines.flow.Flow
import com.tech.api.datasource.InitialResponseDataSource
import com.tech.domain_impl.mapper.toDomainModelList
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class SatelliteUseCaseImpl @Inject constructor(
    private val dataSource: InitialResponseDataSource,
    @IODispatcher private val ioDispatcher: CoroutineContext
) : SatelliteUseCase {
    override fun getSatellites(): Flow<List<SatelliteDomainModel>> =
        flow {
            val satelliteList = (dataSource.getSatellites().toDomainModelList())
            emit(satelliteList)
        }.flowOn(ioDispatcher)
}