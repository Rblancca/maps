package com.maps.contest.data.usecase

import arrow.core.Either
import com.maps.contest.data.entities.ErrorResponse
import com.maps.contest.data.entities.Routers

class GetRouters(private val serviceRepository: MapsRepository) {
    suspend operator fun invoke(
        northEastLatitude: Double,
        northEastLongitude: Double,
        southWestLatitude: Double,
        southWestLongitude: Double
    ): Either<ErrorResponse, List<Routers>> =
        serviceRepository.getRouters(
            northEastLatitude,
            northEastLongitude,
            southWestLatitude,
            southWestLongitude
        )
}