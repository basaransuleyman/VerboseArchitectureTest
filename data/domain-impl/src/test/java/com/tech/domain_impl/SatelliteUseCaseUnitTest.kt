package com.tech.domain_impl

import com.google.common.truth.Truth.assertThat
import com.tech.api.datasource.InitialResponseDataSource
import com.tech.api.model.Satellite
import com.tech.core.rules.MainDispatcherRule
import com.tech.domain.model.SatelliteDomainModel
import com.tech.domain_impl.usecase.SatelliteUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SatelliteUseCaseUnitTest {

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    private lateinit var satelliteUseCase: SatelliteUseCaseImpl
    private val initialResponseDataSource = mockk<InitialResponseDataSource>(relaxed = true)


    @Before
    fun setUp() {
        satelliteUseCase = SatelliteUseCaseImpl(
            dataSource = initialResponseDataSource,
            ioDispatcher = coroutineRule.testDispatcher
        )
    }

    @Test
    fun `getSatellites returns list of SatelliteDomainModel when data is available`() = runTest {
        // Given
        val mockSatelliteDataList = listOf(Satellite(1, true, "Hubble"))
        val expectedDomainModelList = listOf(SatelliteDomainModel(1, true, "Hubble"))
        coEvery { initialResponseDataSource.getSatellites() } returns mockSatelliteDataList

        // When
        val result = satelliteUseCase.getSatellites().toList()

        // Then
        assertThat(result).isEqualTo(listOf(expectedDomainModelList))
    }
}

