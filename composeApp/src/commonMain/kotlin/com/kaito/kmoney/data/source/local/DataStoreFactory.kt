package com.kaito.kmoney.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

fun createDataStore(storePath: () -> String): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = {
            storePath.invoke().toPath()
        }
    )

internal const val DATA_STORE_FILE_NAME = "app.preferences_pb"