package com.example.moviecatalogue

import android.app.Application
import com.example.moviecatalogue.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(appComponent)
        }
    }
}