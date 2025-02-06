package uz.toshmatov.bookpro.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import uz.toshmatov.bookpro.core.logger.logInfo
import uz.toshmatov.bookpro.data.local.model.ThemeMode

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "top_teacher_theme")
private val THEME_MODE_KEY = stringPreferencesKey("theme")
private const val LIGHT = "Light"
private const val DARK = "Dark"
private const val SYSTEM = "System"

class AppDataStore(context: Context) {
    private val dataStore = context.dataStore

    fun getThemeMode(): Flow<ThemeMode> {
        return dataStore.data
            .map { preferences ->
                when (preferences[THEME_MODE_KEY]) {
                    LIGHT -> ThemeMode.Light
                    DARK -> ThemeMode.Dark
                    SYSTEM -> ThemeMode.System
                    else -> ThemeMode.System
                }
            }.flowOn(Dispatchers.IO)
    }

    suspend fun setThemeMode(themeMode: ThemeMode) {
        val themeName = when (themeMode) {
            ThemeMode.Light -> LIGHT
            ThemeMode.Dark -> DARK
            ThemeMode.System -> SYSTEM
        }

        dataStore.edit { preferences ->
            preferences[THEME_MODE_KEY] = themeName
        }
    }

    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}