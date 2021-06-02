package com.maps.contest.data.repository

import arrow.core.Either
import com.maps.contest.data.entities.ErrorResponse
import com.maps.contest.data.entities.Routers

interface MapsDataSource {
    suspend fun getRouters(
        northEastLatitude: Double,
        northEastLongitude: Double,
        southWestLatitude: Double,
        southWestLongitude: Double
    ): Either<ErrorResponse, List<Routers>>
}