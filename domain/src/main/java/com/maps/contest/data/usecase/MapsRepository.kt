package com.maps.contest.data.usecase

import arrow.core.Either
import com.maps.contest.data.entities.ErrorResponse
import com.maps.contest.data.entities.Routers

interface MapsRepository {
    suspend fun getRouters(
        northEastLatitude: Double,
        northEastLongitude: Double,
        southWestLatitude: Double,
        southWestLongitude: Double
    ): Either<ErrorResponse, List<Routers>>
}