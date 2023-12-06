package com.tech.persistence

import android.content.SharedPreferences
import com.google.gson.Gson
import com.tech.core.rules.MainDispatcherRule
import com.tech.persistence.storage.ClientStorageImpl
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ClientStorageUnitTest {

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    private lateinit var clientStorage: ClientStorageImpl
    private val gson = Gson()
    private val sharedPreferences = mockk<SharedPreferences>(relaxed = true)
    private val sharedPreferencesEditor = mockk<SharedPreferences.Editor>(relaxed = true)

    @Before
    fun setUp() {
        clientStorage = ClientStorageImpl(mockk(), gson, sharedPreferences)

        every { sharedPreferences.edit() } returns sharedPreferencesEditor
        every { sharedPreferencesEditor.putString(any(), any()) } returns sharedPreferencesEditor
        every { sharedPreferencesEditor.apply() } just Runs
    }

    @Test
    fun `checkMemoryForJson returns data when available in cache`() {
        val satelliteId = 1
        val satelliteDetail = SatelliteDetailResponse(satelliteId, 100, 1000, 500000, "2020-01-01")
        val satelliteJson = gson.toJson(satelliteDetail)

        every {
            sharedPreferences.getString(
                "SatelliteDetail_$satelliteId",
                null
            )
        } returns satelliteJson

        val result = clientStorage.checkMemoryForJson(satelliteId)

        assertEquals(satelliteDetail, result)
    }

    @Test
    fun `saveJsonToMemory saves data correctly to cache`() {
        val satelliteId = 1
        val satelliteDetail = SatelliteDetailResponse(satelliteId, 100, 1000, 500000, "2020-01-01")
        val satelliteJson = gson.toJson(satelliteDetail)

        clientStorage.saveJsonToMemory(satelliteId, satelliteDetail)

        verify { sharedPreferencesEditor.putString("SatelliteDetail_$satelliteId", satelliteJson) }
        verify { sharedPreferencesEditor.apply() }
    }

}