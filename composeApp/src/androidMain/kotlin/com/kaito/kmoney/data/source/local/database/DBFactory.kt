package com.kaito.kmoney.data.source.local.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

fun createDatabase(ctx: Context): AppDatabase {
    val appCtx = ctx.applicationContext
    val dbFile = appCtx.getDatabasePath("app_db")
    return Room.databaseBuilder<AppDatabase>(
        context = appCtx,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}