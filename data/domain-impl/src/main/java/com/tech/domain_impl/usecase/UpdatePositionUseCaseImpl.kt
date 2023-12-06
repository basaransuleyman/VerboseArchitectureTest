package com.tech.domain_impl.usecase

import com.tech.core.utils.IODispatcher
import com.tech.domain.model.Position
import com.tech.domain.usecase.UpdatePositionUseCase
import com.tech.persistence.storage.ClientStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class UpdatePositionUseCaseImpl @Inject constructor(
    private val localSource: ClientStorage,
    @IODispatcher private val ioDispatcher: CoroutineContext
) : UpdatePositionUseCase {
    override fun updatePosition(id: Int): Flow<List<Position>> = flow {
        emit(localSource.getPositionsForSatellite(id))
    }.flowOn(ioDispatcher)
}
