package com.maps.contest.data.response

import com.google.gson.annotations.SerializedName
import com.maps.contest.data.entities.Routers
import com.maps.contest.data.util.Mappable

data class RoutersResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("x")
    val x: Double,
    @SerializedName("y")
    val y: Double,
    @SerializedName("scheduledArrival")
    val scheduledArrival: Int,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("locationType")
    val locationType: Int,
    @SerializedName("companyZoneId")
    val companyZoneId: Int
) : Mappable<Routers> {
    override fun toDomain(): Routers =
        Routers(id, name, x, y, scheduledArrival, lon, lat, companyZoneId)
}
