package com.kaito.kmoney

import com.kaito.kmoney.data.di.dataModule
import com.kaito.kmoney.data.di.dataPlatformModule
import com.kaito.kmoney.ui.uiModule

val appModule = listOf(dataPlatformModule, dataModule, uiModule)
