package com.kaito.kmoney.data.di

import com.kaito.kmoney.data.repository.ISettingRepo
import com.kaito.kmoney.data.repository.SettingRepoImpl
import com.kaito.kmoney.data.source.remote.provideClient
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

expect val dataPlatformModule: Module

val dataModule = module {
    single<HttpClient> {
        provideClient()
    }

    single<ISettingRepo> {
        SettingRepoImpl(get())
    }
}