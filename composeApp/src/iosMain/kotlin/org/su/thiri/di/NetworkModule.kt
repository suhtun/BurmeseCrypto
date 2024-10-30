package org.su.thiri.di

import com.su.coinproject.core.data.remote.HttpClientFactory
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual val networkModule  = module{
    single {
        HttpClientFactory.create(Darwin.create())
    }
}