package uz.toshmatov.bookpro.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.toshmatov.bookpro.data.local.model.ThemeMode

interface DataStoreRepository {
    fun getThemeMode(): Flow<ThemeMode>

    suspend fun setThemeMode(themeMode: ThemeMode)
}