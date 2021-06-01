package com.maps.contest.view.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.maps.contest.myapplication.R
import com.maps.contest.myapplication.databinding.MapFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : Fragment() {
    private var _binding: MapFragmentBinding? = null
    private val viewModel by viewModel<MapViewModel>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MapFragmentBinding.inflate(inflater, container, false)
        setObserver()
        return binding.root
    }

    private fun setObserver() {
        viewModel.routers.observe(viewLifecycleOwner, { list ->
            binding.root.let { root ->
                Snackbar.make(
                    root,
                    "exito",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })
        viewModel.error.observe(viewLifecycleOwner, {
            binding.root.let { root ->
                Snackbar.make(
                    root,
                    "error",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}