package com.kaito.app

import com.kaito.app.data.di.dataModule
import com.kaito.app.ui.di.uiModule

val appModule = listOf(dataModule, uiModule)
