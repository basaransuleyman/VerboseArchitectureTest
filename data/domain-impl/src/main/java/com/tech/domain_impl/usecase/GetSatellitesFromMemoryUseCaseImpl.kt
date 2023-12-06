package com.tech.domain_impl.usecase

import com.tech.core.IODispatcher
import com.tech.domain.model.SatelliteDetailDomainModel
import com.tech.domain.usecase.GetSatellitesFromMemoryUseCase
import com.tech.domain_impl.mapper.toDomainModel
import com.tech.persistence.storage.ClientStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class GetSatellitesFromMemoryUseCaseImpl @Inject constructor(
    private val localSource: ClientStorage,
    @IODispatcher private val ioDispatcher: CoroutineContext
) : GetSatellitesFromMemoryUseCase {
    override fun getSatellitesFromMemory(id: Int): Flow<SatelliteDetailDomainModel> = flow {
        localSource.getSatelliteDetail(id)?.toDomainModel()?.let { emit(it) }
    }.flowOn(ioDispatcher)
}
