package com.kaito.kmoney.data.local

import com.kaito.kmoney.data.source.local.DATA_STORE_FILE_NAME
import com.kaito.kmoney.data.source.local.createDataStore
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
fun provideDataStore() = createDataStore {
    val documentDir: NSURL? = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )
    requireNotNull(documentDir).path + "/${DATA_STORE_FILE_NAME}"
}