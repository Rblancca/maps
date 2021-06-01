package com.maps.contest.data.repository

import com.maps.contest.data.response.RoutersResponse
import retrofit2.Response
import retrofit2.http.GET

interface MapService {
   @GET("v1/routers/lisboa/resources?lowerLeftLatLon=38.711046,-9.160096&upperRightLatLon=38.739429,-9.137115")
    suspend fun getRouters(): Response<List<RoutersResponse>>
}