package com.maps.contest.view.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maps.contest.data.entities.ErrorResponse
import com.maps.contest.data.entities.Routers
import com.maps.contest.data.usecase.GetRouters
import kotlinx.coroutines.launch

class MapViewModel(private val getRouters: GetRouters) : ViewModel() {
    private val _routers = MutableLiveData<List<Routers>>()
    val routers: LiveData<List<Routers>> = _routers

    private val _error = MutableLiveData<ErrorResponse>()
    val error: LiveData<ErrorResponse> = _error


    fun loadMarkets(
        northEastLatitude: Double,
        northEastLongitude: Double,
        southWestLatitude: Double,
        southWestLongitude: Double
    ) {
        viewModelScope.launch {
            getRouters(
                northEastLatitude,
                northEastLongitude,
                southWestLatitude,
                southWestLongitude
            ).fold({ error ->
                _error.postValue(error)
            }, { domainRoutes ->
                _routers.postValue(domainRoutes)
            })
        }
    }
}