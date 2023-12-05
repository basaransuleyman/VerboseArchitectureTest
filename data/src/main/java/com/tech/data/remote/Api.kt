package com.tech.data.remote

import com.tech.data.model.Satellite
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("/basaransuleyman/suleyman-basaranoglu-json/main/list")
    suspend fun getInitialData(): Response<List<Satellite>>
}