package com.kaito.kmoney.ui

import com.kaito.kmoney.ui.screen.splash.SplashViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::SplashViewModel)
}