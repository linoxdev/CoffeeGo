package com.alonso.data.local.preferences

import androidx.datastore.preferences.core.edit
import com.alonso.data.local.preferences.DataStoreUtil.Companion.IS_DARK_MODE_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesRepository(
    private val dataStoreUtil: DataStoreUtil
) {

    val themeConfig: Flow<Boolean> = dataStoreUtil.dataStore.data
        .map { it[IS_DARK_MODE_KEY] ?: false }

    suspend fun toggleTheme(isDark: Boolean) {
        dataStoreUtil.dataStore.edit { it[IS_DARK_MODE_KEY] = isDark }
    }
}