package com.kaito.kmoney

import com.kaito.kmoney.data.di.dataModule
import com.kaito.kmoney.data.di.dataPlatformModule
import com.kaito.kmoney.ui.di.uiModule

val appModule = listOf(dataModule, dataPlatformModule, uiModule)
