package com.tech.api

import com.tech.api.model.Satellite
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET(BuildConfig.GET_API_REQUEST)
    suspend fun getInitialData(): Response<List<Satellite>>
}