package com.kaito.app.data.di

import com.kaito.app.data.source.remote.provideClient
import io.ktor.client.HttpClient
import org.koin.dsl.module

val dataModule = module {
    single<HttpClient> {
        provideClient()
    }
}