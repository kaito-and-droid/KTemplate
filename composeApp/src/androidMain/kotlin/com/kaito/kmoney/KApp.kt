package com.kaito.kmoney

import android.app.Application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KApp: Application() {

    override fun onCreate() {
        super.onCreate()

        Napier.base(DebugAntilog())
        startKoin {
            androidContext(this@KApp)
            modules(appModule)
            androidLogger()
        }
    }
}