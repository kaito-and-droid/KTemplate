package com.kaito.kmoney.data.di

import com.kaito.kmoney.data.repository.IUserRepo
import com.kaito.kmoney.data.repository.UserRepoImpl
import com.kaito.kmoney.data.source.remote.provideClient
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModule = module {
    single<HttpClient> {
        provideClient()
    }

    single<IUserRepo> { UserRepoImpl(get()) }
}

expect val dataPlatformModule: Module