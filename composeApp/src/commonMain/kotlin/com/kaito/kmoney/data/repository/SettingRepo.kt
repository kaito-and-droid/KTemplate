package com.kaito.kmoney.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class AppSetting(
    val isDarkTheme: Boolean = false,
    val isEnableNotification: Boolean = false,
    val isPremium: Boolean = false
)

private val KEY_NOTI = booleanPreferencesKey("key_notification")
private val KEY_DARK_THEME = booleanPreferencesKey("key_dark_theme")
private val KEY_PREMIUM = booleanPreferencesKey("key_premium")

interface ISettingRepo {
    fun getAppSettings(): Flow<AppSetting>
    suspend fun setEnableNotification(value: Boolean)

    suspend fun setPremium(value: Boolean)

    suspend fun setDarkTheme(value: Boolean)
}

class SettingRepoImpl(
    private val dataStore: DataStore<Preferences>
) : ISettingRepo {

    override fun getAppSettings(): Flow<AppSetting> =
        dataStore.data.map {
            val isDarkTheme = it[KEY_DARK_THEME] ?: false
            val isNoti = it[KEY_NOTI] ?: false
            val isPremium = it[KEY_PREMIUM] ?: false

            AppSetting(
                isDarkTheme = isDarkTheme,
                isEnableNotification = isNoti,
                isPremium = isPremium
            )
        }

    override suspend fun setEnableNotification(value: Boolean) {
        dataStore.edit { prefs ->
            prefs[KEY_NOTI] = value
        }
    }

    override suspend fun setPremium(value: Boolean) {
        dataStore.edit { prefs ->
            prefs[KEY_PREMIUM] = value
        }
    }

    override suspend fun setDarkTheme(value: Boolean) {
        dataStore.edit { prefs ->
            prefs[KEY_DARK_THEME] = value
        }
    }
}