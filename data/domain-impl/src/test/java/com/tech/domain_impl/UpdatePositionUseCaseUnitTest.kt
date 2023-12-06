package com.tech.domain_impl

import com.google.common.truth.Truth.assertThat
import com.tech.core.rules.MainDispatcherRule
import com.tech.domain.model.Position
import com.tech.domain_impl.usecase.UpdatePositionUseCaseImpl
import com.tech.persistence.storage.ClientStorage
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UpdatePositionUseCaseUnitTest {

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    private lateinit var updatePositionUseCase: UpdatePositionUseCaseImpl
    private val clientStorage = mockk<ClientStorage>(relaxed = true)

    @Before
    fun setUp() {
        updatePositionUseCase = UpdatePositionUseCaseImpl(
            localSource = clientStorage,
            ioDispatcher = coroutineRule.testDispatcher
        )
    }

    @Test
    fun `updatePosition returns list of positions for given satellite id`() = runTest {
        // Given
        val satelliteId = 1
        val mockPositions = listOf(Position(0.1, 0.2), Position(0.3, 0.4))
        coEvery { clientStorage.getPositionsForSatellite(satelliteId) } returns mockPositions

        // When
        val result = updatePositionUseCase.updatePosition(satelliteId).toList()

        // Then
        assertThat(result).isEqualTo(listOf(mockPositions))
    }

}