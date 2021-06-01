package com.maps.contest.di

import com.maps.contest.data.usecase.MapsRepository
import com.maps.contest.view.map.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class ViewModelModule {
    fun getModule() = module {
        viewModel { MapViewModel(get()) }
    }
}