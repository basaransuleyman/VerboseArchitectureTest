package com.tech.presentation

import com.google.common.truth.Truth.assertThat
import com.tech.core.rules.MainDispatcherRule
import com.tech.domain.model.SatelliteDetailDomainModel
import com.tech.domain.usecase.GetSatellitesFromMemoryUseCase
import com.tech.domain.usecase.UpdatePositionUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SatelliteDetailViewModelUnitTest {

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    private lateinit var viewModel: SatelliteDetailViewModel
    private val getSatellitesFromMemoryUseCase: GetSatellitesFromMemoryUseCase =
        mockk(relaxed = true)
    private val updatePositionUseCase: UpdatePositionUseCase = mockk(relaxed = true)

    @Before
    fun setUp() {
        viewModel = SatelliteDetailViewModel(getSatellitesFromMemoryUseCase, updatePositionUseCase)
    }

    @Test
    fun `loadSatelliteDetails emits correct satellite details`() =
        runTest(coroutineRule.testDispatcher) {
            val satelliteID = 123
            val mockSatelliteDetails =
                SatelliteDetailDomainModel(
                    id = 1,
                    costPerLaunch = 100,
                    firstFlight = "asd",
                    height = 111,
                    mass = 222
                )
            coEvery { getSatellitesFromMemoryUseCase.getSatellitesFromMemory(satelliteID) } returns flowOf(
                mockSatelliteDetails
            )

            val results = mutableListOf<SatelliteDetailDomainModel>()
            val job = launch {
                viewModel.satelliteDetails.collect { results.add(it) }
            }

            viewModel.loadSatelliteDetails(satelliteID)

            assertThat(results).isNotEmpty()
            assertThat(results.first()).isEqualTo(mockSatelliteDetails)

            job.cancel()
        }
}