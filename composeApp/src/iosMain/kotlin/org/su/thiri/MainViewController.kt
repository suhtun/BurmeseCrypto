package org.su.thiri

import androidx.compose.ui.window.ComposeUIViewController
import org.su.thiri.di.appModule
import org.koin.core.context.startKoin
import org.su.thiri.di.networkModule

fun MainViewController() = ComposeUIViewController(
    configure = {
        startKoin {
            modules(appModule, networkModule)
        }
    }
) { App() }