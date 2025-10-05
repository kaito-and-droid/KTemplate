package com.kaito.kmoney.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.kaito.kmoney.data.local.provideDataStore
import org.koin.dsl.module

actual val dataPlatformModule = module {

    single<DataStore<Preferences>> {
        provideDataStore()
    }
}