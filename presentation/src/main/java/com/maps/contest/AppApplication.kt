package com.maps.contest

import RetrofitModule
import android.app.Application
import com.maps.contest.data.di.DatabaseModule
import com.maps.contest.data.di.DomainModule
import com.maps.contest.di.ViewModelModule
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
                    ViewModelModule().getModule(),
                    DomainModule().getModule(),
                    DatabaseModule().getModule()
                )
            )
        }
    }
}