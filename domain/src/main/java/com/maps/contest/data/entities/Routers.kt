package com.maps.contest.data.entities

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class Routers(
    val id: String,
    val name: String,
    val x: Double,
    val y: Double,
    val scheduledArrival: Int,
    val lon: Double,
    val lat: Double,
    val companyZoneId: Int,
    val color: Float
) : ClusterItem {
    override fun getPosition(): LatLng =
        LatLng(lat, lon)

    override fun getTitle(): String =
        name

    override fun getSnippet(): String =
        name
}
