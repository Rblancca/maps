package com.maps.contest.data.di

import com.maps.contest.data.usecase.GetRouters
import org.koin.dsl.module

class DomainModule {
    fun getModule() = module {
        factory { GetRouters(get()) }
    }
}