package com.tech.domain_impl

import com.google.common.truth.Truth.assertThat
import com.tech.core.rules.MainDispatcherRule
import com.tech.domain_impl.mapper.toDomainModel
import com.tech.domain_impl.usecase.GetSatellitesFromMemoryUseCaseImpl
import com.tech.persistence.SatelliteDetailResponse
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
class GetSatellitesFromMemoryUseCaseUnitTest {

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    private lateinit var useCase: GetSatellitesFromMemoryUseCaseImpl
    private val clientStorage = mockk<ClientStorage>(relaxed = true)

    @Before
    fun setUp() {
        useCase = GetSatellitesFromMemoryUseCaseImpl(clientStorage, coroutineRule.testDispatcher)
    }

    @Test
    fun `getSatellitesFromMemory emits SatelliteDetailDomainModel when data is available`() = runTest {
        val satelliteId = 1
        val satelliteDetailResponse = SatelliteDetailResponse(
            id = satelliteId,
            costPerLaunch = 100,
            firstFlight = "2021-01-01",
            height = 100,
            mass = 1000
        )

        val satelliteDetailDomainModel = satelliteDetailResponse.toDomainModel()

        coEvery { clientStorage.getSatelliteDetail(satelliteId) } returns satelliteDetailResponse

        val result = useCase.getSatellitesFromMemory(satelliteId).toList()

        assertThat(result).containsExactly(satelliteDetailDomainModel)
    }

    @Test
    fun `getSatellitesFromMemory emits nothing when data is not available`() = runTest {
        val satelliteId = 1
        coEvery { clientStorage.getSatelliteDetail(satelliteId) } returns null

        val result = useCase.getSatellitesFromMemory(satelliteId).toList()

        assertThat(result).isEmpty()
    }
}