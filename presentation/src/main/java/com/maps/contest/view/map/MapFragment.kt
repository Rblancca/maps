package com.maps.contest.view.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.Snackbar
import com.maps.contest.myapplication.R
import com.maps.contest.myapplication.databinding.MapFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : Fragment(), MapProvider, OnMapReadyListener, OnCameraIdleListener {
    private var _binding: MapFragmentBinding? = null
    private lateinit var map: MapViewComponent
    private val mapViewFragmentLifecycleCallback = MapViewFragmentLifecycleCallback
    private val viewModel by viewModel<MapViewModel>()
    private var lastCameraPosition = LatLng(.0, .0)

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MapFragmentBinding.inflate(inflater, container, false)
        map =
            binding.googleMap.apply {
                setOnMapReadyListener(this@MapFragment)
                setOnCameraIdleListener(this@MapFragment)
            }
        setObserver()
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().supportFragmentManager.registerFragmentLifecycleCallbacks(
            mapViewFragmentLifecycleCallback, true
        )
    }


    private fun setObserver() {
        viewModel.routers.observe(viewLifecycleOwner, { list ->
            map.loadMarkers(list)
        })
        viewModel.error.observe(viewLifecycleOwner, {
            binding.root.let { root ->
                Snackbar.make(
                    root,
                    R.string.error_alert,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })
    }

    override fun getMapView(): MapViewComponent = map

    override fun onMapReady() {
        map.focusAtLocation(LatLng(38.711046, -9.160096)) {
            loadMarket()
        }
    }

    private fun loadMarket() {
        val bounds = map.getBounds()
        if (bounds.center.latitude - lastCameraPosition.latitude > 0.00005 || bounds.center.longitude - lastCameraPosition.longitude > 0.0005) {
            lastCameraPosition = bounds.center
            viewModel.loadMarkets(
                bounds.northeast.latitude,
                bounds.northeast.longitude,
                bounds.southwest.latitude,
                bounds.southwest.longitude
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().supportFragmentManager.unregisterFragmentLifecycleCallbacks(
            mapViewFragmentLifecycleCallback
        )
    }

    override fun onCameraMove() {
        loadMarket()
    }
}