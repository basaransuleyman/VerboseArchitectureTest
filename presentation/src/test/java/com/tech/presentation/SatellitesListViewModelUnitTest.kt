package com.tech.presentation

import com.google.common.truth.Truth.assertThat
import com.tech.core.rules.MainDispatcherRule
import com.tech.domain.model.SatelliteDomainModel
import com.tech.domain.usecase.SatelliteUseCase
import com.tech.presentation.mapper.toUiModelList
import com.tech.presentation.state.SatelliteListViewState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.fail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SatellitesListViewModelUnitTest {

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    private lateinit var viewModel: SatellitesListViewModel
    private val satelliteUseCase: SatelliteUseCase = mockk(relaxed = true)

    @Before
    fun setup() {
        viewModel = SatellitesListViewModel(satelliteUseCase)
    }

    @Test
    fun `loadSatellites emits Loading and then Success`() = runTest {
        val mockDomainModels = listOf<SatelliteDomainModel>()

        coEvery { satelliteUseCase.getSatellites() } returns flow {
            emit(mockDomainModels)
        }

        val results = mutableListOf<SatelliteListViewState>()
        val job = launch {
            viewModel.satelliteListState.collect { results.add(it) }
        }

        viewModel.loadSatellites()

        advanceTimeBy(1000)

        if (results.isNotEmpty()) {
            assertThat(results[0]).isEqualTo(SatelliteListViewState.Loading)
            val expectedUiModels = mockDomainModels.toUiModelList()
            assertThat(results[1]).isEqualTo(SatelliteListViewState.Success(expectedUiModels))
        } else {
            fail("No items were emitted by the ViewModel")
        }

        job.cancel()
    }

    @Test
    fun `startSearch updates satelliteListState based on searchQuery`() = runTest {
        val mockSatellites = listOf(
            SatelliteDomainModel(1, true, "Hubble"),
            SatelliteDomainModel(2, false, "James Webb")
        )
        coEvery { satelliteUseCase.getSatellites() } returns flowOf(mockSatellites)

        val job = launch {
            viewModel.satelliteListState.collect { /* Collecting states */ }
        }

        viewModel.startSearch()

        // Change the search query and advance time for debounce
        viewModel.searchQuery.value = "Hubble"
        advanceTimeBy(400)

        // Assertion
        assertThat(viewModel.satelliteListState.value).isInstanceOf(SatelliteListViewState.Success::class.java)

        job.cancel()
    }

    @Test
    fun `updateSearchQuery function update the search query`() = runTest {
        val query = "Req"
        viewModel.updateSearchQuery(query)

        assertEquals(query, viewModel.searchQuery.first())
    }
}