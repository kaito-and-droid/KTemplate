package com.kaito.kmoney.data.di

import com.kaito.kmoney.data.source.local.dao.UserDao
import com.kaito.kmoney.data.source.local.database.AppDatabase
import com.kaito.kmoney.data.source.local.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val dataPlatformModule: Module = module {
    single<AppDatabase> {
        getDatabaseBuilder().build()
    }

    single<UserDao> {
        get<AppDatabase>().userDao()
    }
}