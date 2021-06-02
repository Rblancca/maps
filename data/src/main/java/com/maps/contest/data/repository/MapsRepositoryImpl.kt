package com.maps.contest.data.repository

import arrow.core.Either
import com.maps.contest.data.entities.ErrorResponse
import com.maps.contest.data.entities.Routers
import com.maps.contest.data.usecase.MapsRepository

class MapsRepositoryImpl(private val serviceDataSource: MapsDataSource) : MapsRepository {
    override suspend fun getRouters(
        northEastLatitude: Double,
        northEastLongitude: Double,
        southWestLatitude: Double,
        southWestLongitude: Double
    ): Either<ErrorResponse, List<Routers>> =
        serviceDataSource.getRouters(northEastLatitude,northEastLongitude,southWestLatitude,southWestLongitude)
}