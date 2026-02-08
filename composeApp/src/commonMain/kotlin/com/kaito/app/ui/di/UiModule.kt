package com.kaito.app.ui.di

import com.kaito.app.ui.screen.splash.SplashViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::SplashViewModel)
}