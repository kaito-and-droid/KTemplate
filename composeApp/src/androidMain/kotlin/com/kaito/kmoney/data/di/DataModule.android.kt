package com.kaito.kmoney.data.di

import com.kaito.kmoney.data.source.local.dao.UserDao
import com.kaito.kmoney.data.source.local.database.AppDatabase
import com.kaito.kmoney.data.source.local.database.createDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val dataPlatformModule: Module = module {
    single<AppDatabase> {
        createDatabase(get())
    }

    single<UserDao> {
        get<AppDatabase>().userDao()
    }
}