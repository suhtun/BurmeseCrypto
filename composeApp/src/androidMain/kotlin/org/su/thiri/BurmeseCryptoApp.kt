package org.su.thiri

import android.app.Application
import org.su.thiri.di.appModule
import org.koin.core.context.GlobalContext.startKoin
import org.su.thiri.di.networkModule

class BurmeseCryptoApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule, networkModule)
        }
    }
}