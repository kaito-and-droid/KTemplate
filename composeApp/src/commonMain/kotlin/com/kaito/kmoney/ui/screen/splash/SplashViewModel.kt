package com.kaito.kmoney.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaito.kmoney.data.repository.AppSetting
import com.kaito.kmoney.data.repository.ISettingRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SplashViewModel() : ViewModel(), KoinComponent {

    private val settingRepo: ISettingRepo by inject()

    val appSettings: StateFlow<AppSetting> =
        settingRepo.getAppSettings()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(500),
                initialValue = AppSetting()
            )

    fun setEnableNoti(value: Boolean) = viewModelScope.launch {
        settingRepo.setEnableNotification(value)
    }

    fun setPremium(value: Boolean) = viewModelScope.launch {
        settingRepo.setPremium(value)
    }

    fun setDarkTheme(value: Boolean) = viewModelScope.launch {
        settingRepo.setDarkTheme(value)
    }
}