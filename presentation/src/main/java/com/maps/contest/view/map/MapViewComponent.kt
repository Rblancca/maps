package com.maps.contest.view.map

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.*
import com.google.maps.android.clustering.ClusterManager
import com.maps.contest.data.entities.Routers
import com.maps.contest.myapplication.R
import com.maps.contest.myapplication.databinding.MapViewComponentBinding

class MapViewComponent(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private val mapView: MapView
    private lateinit var map: GoogleMap
    private var onMapReadyListener: OnMapReadyListener? = null
    private var onCameraIdleListener: OnCameraIdleListener? = null
    private lateinit var routers: List<Routers>

    init {
        val binding = MapViewComponentBinding.inflate(LayoutInflater.from(context), this, true)
        mapView = binding.googleMap
    }

    fun setOnMapReadyListener(onMapReadyListener: OnMapReadyListener) {
        this.onMapReadyListener = onMapReadyListener
    }

    fun setOnCameraIdleListener(onCameraIdleListener: OnCameraIdleListener) {
        this.onCameraIdleListener = onCameraIdleListener
    }

    fun loadMarkers(markers: List<Routers>) {
        routers = markers.sortedBy { it.companyZoneId }
        map.clear()
        addClusteredMarkers(map)
    }


    fun onCreate(savedInstanceState: Bundle?) {
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync {
            this.map = it.apply {
                uiSettings.apply {
                    isMapToolbarEnabled = false
                    isMyLocationButtonEnabled = false
                    isCompassEnabled = true
                }
                setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.style_json))

            }
            onMapReadyListener?.onMapReady()
        }
    }

    private fun addClusteredMarkers(googleMap: GoogleMap) {
        val clusterManager = ClusterManager<Routers>(context, googleMap)
        clusterManager.renderer =
            RoutersRenderer(
                context,
                googleMap,
                clusterManager
            )

        clusterManager.addItems(routers)
        clusterManager.cluster()

        map.setOnCameraIdleListener {
            onCameraIdleListener?.onCameraMove()
            clusterManager.onCameraIdle()
        }

    }

    fun focusAtLocation(
        location: LatLng,
        zoom: Float = 15f,
        animated: Boolean = true,
        onFocusEnd: (() -> Unit)? = null
    ) {
        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(location.latitude, location.longitude))
            .zoom(zoom)
            .build()
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition)
        if (animated) map.animateCamera(cameraUpdate, object : GoogleMap.CancelableCallback {
            override fun onFinish() {
                onFocusEnd?.invoke()
            }

            override fun onCancel() {
                onFocusEnd?.invoke()
            }
        }) else map.moveCamera(cameraUpdate)
    }

    fun getBounds(): LatLngBounds = map.projection.visibleRegion.latLngBounds

    fun onStart() {
        mapView.onStart()
    }

    fun onResume() {
        mapView.onResume()
    }

    fun onPause() {
        mapView.onPause()
    }

    fun onStop() {
        mapView.onStop()
    }

    fun onDestroy() {
        mapView.onDestroy()
    }

    fun onSaveInstanceState(outState: Bundle) {
        mapView.onSaveInstanceState(outState)
    }
}