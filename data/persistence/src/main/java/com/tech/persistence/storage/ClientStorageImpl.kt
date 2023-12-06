package com.tech.persistence.storage

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tech.domain.model.Position
import com.tech.domain.model.SatellitePositionsList
import com.tech.persistence.SatelliteDetailResponse
import com.tech.persistence.extension.getJsonDataFromAsset
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class ClientStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson,
    private val sharedPreferences: SharedPreferences
) : ClientStorage {
    override suspend fun getSatelliteDetail(id: Int): SatelliteDetailResponse? {
        checkMemoryForJson(id)?.let {
            // Return the value found from cache if it exists
            return it
        }
        // If not found in cache, read from JSON
        val jsonDetailString = context.getJsonDataFromAsset("detail.json")
        val type = object : TypeToken<List<SatelliteDetailResponse>>() {}.type
        val satelliteList = gson.fromJson<List<SatelliteDetailResponse>>(jsonDetailString, type)
        val satelliteDetail = satelliteList.firstOrNull { it.id == id }

        saveJsonToMemory(id, satelliteDetail)
        return satelliteDetail
    }

    override suspend fun getPositionsForSatellite(id: Int): List<Position> {
        val jsonString = context.getJsonDataFromAsset("positions.json")
        val type = object : TypeToken<SatellitePositionsList>() {}.type
        val response = gson.fromJson<SatellitePositionsList>(jsonString, type)
        return response.list.firstOrNull { it.id == id.toString() }?.positions ?: emptyList()
    }

    private fun checkMemoryForJson(id: Int): SatelliteDetailResponse? {
        return sharedPreferences.getString("SatelliteDetail_$id", null)?.let {
            // Convert JSON string to SatelliteDetailDomainModel
            gson.fromJson(it, SatelliteDetailResponse::class.java)
        }
    }

    private fun saveJsonToMemory(id: Int, satelliteDetail: SatelliteDetailResponse?) {
        satelliteDetail?.let {
            // Convert SatelliteDetailDomainModel to JSON string and save
            sharedPreferences.edit()
                .putString("SatelliteDetail_$id", gson.toJson(it))
                .apply()
        }
    }
}
