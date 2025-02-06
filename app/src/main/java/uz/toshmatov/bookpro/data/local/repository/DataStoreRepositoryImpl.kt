package uz.toshmatov.bookpro.data.local.repository

import kotlinx.coroutines.flow.Flow
import uz.toshmatov.bookpro.data.local.datastore.AppDataStore
import uz.toshmatov.bookpro.data.local.model.ThemeMode
import uz.toshmatov.bookpro.domain.repository.DataStoreRepository

class DataStoreRepositoryImpl(
    private val dataStore: AppDataStore
) : DataStoreRepository {
    override fun getThemeMode(): Flow<ThemeMode> {
        return dataStore.getThemeMode()
    }

    override suspend fun setThemeMode(themeMode: ThemeMode) {
        dataStore.setThemeMode(themeMode)
    }
}