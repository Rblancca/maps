package com.maps.contest.data.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.maps.contest.data.entities.ErrorResponse
import com.maps.contest.data.entities.Routers
import com.maps.contest.data.util.listToDomain

class MapsDataSourceImpl(private val services: MapService) : MapsDataSource {
    override suspend fun getRouters(
        northEastLatitude: Double,
        northEastLongitude: Double,
        southWestLatitude: Double,
        southWestLongitude: Double
    ): Either<ErrorResponse, List<Routers>> {
        val response = services.getRouters(
            StringBuilder(northEastLatitude.toString()).append(",").append(northEastLongitude)
                .toString(),
            StringBuilder(southWestLatitude.toString()).append(",").append(southWestLongitude)
                .toString()
        )
        return if (response.isSuccessful) {
            response.body().listToDomain().right()
        } else {
            ErrorResponse(response.errorBody().toString()).left()
        }
    }
}