package com.tech.api

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.tech.api.datasource.InitialResponseDataSourceImpl
import com.tech.api.model.GenericException
import com.tech.api.model.Satellite
import retrofit2.Response

class InitialResponseDataSourceTest {

    private lateinit var dataSource: InitialResponseDataSourceImpl
    private val api = mockk<Api>()

    @Before
    fun setUp() {
        dataSource = InitialResponseDataSourceImpl(api)
    }

    @Test
    fun `getSatellites returns list of satellites when API call is successful`() = runBlocking {
        val mockSatellites = listOf(Satellite(1, true, "Hubble"))
        coEvery { api.getInitialData() } returns Response.success(mockSatellites)

        val result = dataSource.getSatellites()

        assertThat(result).isEqualTo(mockSatellites)
    }

    @Test(expected = GenericException::class)
    fun `getSatellites throws GenericException when API call is unsuccessful`(): Unit = runBlocking {
        coEvery { api.getInitialData() } returns Response.error(404, mockk(relaxed = true))

        dataSource.getSatellites()
    }
}