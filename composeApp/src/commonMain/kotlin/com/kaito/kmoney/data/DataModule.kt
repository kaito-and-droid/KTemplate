package com.kaito.kmoney.data

import com.kaito.kmoney.data.source.remote.provideClient
import io.ktor.client.HttpClient
import org.koin.dsl.module

val dataModule = module {
    single<HttpClient> {
        provideClient()
    }
}