package com.maps.contest

import RetrofitModule
import android.app.Application
import com.maps.contest.di.useCaseModule
import com.maps.contest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@AppApplication)
            modules(
                listOf(
                    RetrofitModule().getModule(),
                    viewModelModule,
                    useCaseModule,
                )
            )
        }
    }
}