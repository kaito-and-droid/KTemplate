package com.kaito.kmoney.data.local

import android.content.Context
import com.kaito.kmoney.data.source.local.DATA_STORE_FILE_NAME
import com.kaito.kmoney.data.source.local.createDataStore

fun provideDataStore(ctx: Context) = createDataStore {
    ctx.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
}