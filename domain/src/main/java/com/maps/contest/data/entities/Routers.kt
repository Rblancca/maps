package com.maps.contest.data.entities

data class Routers(
    val id: String,
    val name: String,
    val x: Double,
    val y: Double,
    val scheduledArrival: Int,
    val lon: Double,
    val lat: Double,
    val companyZoneId: Int
)
