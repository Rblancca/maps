package com.maps.contest.view.map

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.maps.contest.data.entities.Routers


class RoutersRenderer(
    context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<Routers>
) : DefaultClusterRenderer<Routers>(context, map, clusterManager) {

    override fun onBeforeClusterItemRendered(
        item: Routers,
        markerOptions: MarkerOptions
    ) {
        markerOptions.title(item.name)
            .position(LatLng(item.lat, item.lon))
            .icon(BitmapDescriptorFactory.defaultMarker(item.color))

    }

    override fun onClusterItemRendered(clusterItem: Routers, marker: Marker) {
        marker.tag = clusterItem
    }
}