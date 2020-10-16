package com.example.recipeapp

import android.app.Application
import com.example.recipeapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            val appModule= listOf(
                networkModule,
                viewModelModule,
                sharedPrefModule,
                useCaseModule,
                repositoryModule
            )
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}