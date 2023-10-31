package com.diegooliveira.rock_paper_scissors.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppInitialization : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppInitialization)
            modules(listOf(repositoryModule, viewModelModule))
        }
    }
}