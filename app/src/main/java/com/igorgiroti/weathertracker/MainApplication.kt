package com.igorgiroti.weathertracker

import android.app.Application
import com.igorgiroti.weathertracker.di.AppModule
import com.igorgiroti.weathertracker.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                AppModule.appModule,
                NetworkModule.networkModule
            )
        }
    }
}
