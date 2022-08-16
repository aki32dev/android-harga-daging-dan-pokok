package com.mp.harga.core.base

import android.app.Application
import com.chibatching.kotpref.Kotpref
import com.mp.harga.BuildConfig
import com.mp.harga.core.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    networkModule,
                    useCaseModule,
                    viewModelModule,
                    repositoryModule
                )
            )
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}