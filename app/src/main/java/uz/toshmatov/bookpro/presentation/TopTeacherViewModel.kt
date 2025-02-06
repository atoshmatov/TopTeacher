package uz.toshmatov.bookpro.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.toshmatov.bookpro.data.local.model.ThemeMode
import uz.toshmatov.bookpro.domain.repository.DataStoreRepository

class TopTeacherViewModel(
    private val dateStoreRepository: DataStoreRepository,
) : ViewModel() {

    private val _isDarkMode = MutableStateFlow(ThemeMode.System)
    val isDarkMode = _isDarkMode.asStateFlow()

    init {
        getThemeMode()
    }

    private fun getThemeMode() {
        dateStoreRepository.getThemeMode()
            .onEach { themeMode ->
                _isDarkMode.value = themeMode
            }.launchIn(viewModelScope)
    }
}