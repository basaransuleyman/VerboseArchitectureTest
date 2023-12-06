package com.tech.domain_impl.usecase

import com.tech.core.IODispatcher
import com.tech.domain.model.SatelliteDomainModel
import com.tech.domain.usecase.SatelliteUseCase
import com.tech.domain_impl.mapper.InitialDataMapper
import kotlinx.coroutines.flow.Flow
import com.tech.api.datasource.InitialResponseDataSource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class SatelliteUseCaseImpl @Inject constructor(
    private val dataSource: InitialResponseDataSource,
    private val mapper: InitialDataMapper,
    @IODispatcher private val ioDispatcher: CoroutineContext
) : SatelliteUseCase {
    override fun getSatellites(): Flow<List<SatelliteDomainModel>> =
        flow {
            val satelliteList = mapper.mapToDomainModelList(dataSource.getSatellites())
            emit(satelliteList)
        }.flowOn(ioDispatcher)
}