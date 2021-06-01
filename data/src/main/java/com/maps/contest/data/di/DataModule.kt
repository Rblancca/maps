package com.maps.contest.data.di

import com.maps.contest.data.repository.MapsDataSource
import com.maps.contest.data.repository.MapsDataSourceImpl
import com.maps.contest.data.repository.MapsRepositoryImpl
import com.maps.contest.data.usecase.MapsRepository
import org.koin.dsl.module

class DatabaseModule {
    fun getModule() = module {
        single<MapsDataSource> { MapsDataSourceImpl(get()) }
        single<MapsRepository> { MapsRepositoryImpl(get()) }
    }
}