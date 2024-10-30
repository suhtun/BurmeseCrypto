package org.su.thiri.di

import com.su.coinproject.core.data.remote.HttpClientFactory
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

actual val networkModule  = module{
    single {
        HttpClientFactory.create(CIO.create())
    }
}