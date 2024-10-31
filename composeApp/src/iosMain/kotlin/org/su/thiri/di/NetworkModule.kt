package org.su.thiri.di

import org.su.thiri.core.data.remote.HttpClientFactory
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

actual val networkModule  = module{
    single {
        HttpClientFactory.create(Darwin.create())
    }
}