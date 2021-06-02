package com.maps.contest.data.repository

import com.maps.contest.data.response.RoutersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MapService {
    @GET(ROUTERS_LISBOA)
    suspend fun getRouters(
        @Query(LOWER_LEFT) northEast: String,
        @Query(UPPER_RIGHT) southWest: String,
    ): Response<List<RoutersResponse>>

    companion object {
        private const val ROUTERS_LISBOA = "v1/routers/lisboa/resources"
        private const val LOWER_LEFT = "lowerLeftLatLon"
        private const val UPPER_RIGHT = "upperRightLatLon"
    }
}