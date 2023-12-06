package com.tech.domain.usecase

import com.tech.domain.model.Position
import kotlinx.coroutines.flow.Flow

interface UpdatePositionUseCase {
    fun updatePosition(id: Int): Flow<List<Position>>
}