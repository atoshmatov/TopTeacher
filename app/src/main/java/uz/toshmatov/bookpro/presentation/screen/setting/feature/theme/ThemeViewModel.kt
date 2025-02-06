package uz.toshmatov.bookpro.presentation.screen.setting.feature.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.toshmatov.bookpro.core.utils.drawable
import uz.toshmatov.bookpro.core.utils.string
import uz.toshmatov.bookpro.data.local.model.ThemeMode
import uz.toshmatov.bookpro.domain.repository.DataStoreRepository
import uz.toshmatov.bookpro.presentation.screen.setting.feature.theme.component.ThemeModel
import uz.toshmatov.bookpro.presentation.screen.setting.feature.theme.intents.ThemeEvents
import uz.toshmatov.bookpro.presentation.screen.setting.feature.theme.intents.ThemeState
import javax.inject.Inject

class ThemeViewModel(
    private val storeRepository: DataStoreRepository
) : ViewModel() {

    private val _state: MutableStateFlow<ThemeState> = MutableStateFlow(ThemeState())
    val state: StateFlow<ThemeState> = _state.asStateFlow()

    private val themeList = listOf(
        ThemeModel(
            title = string.settings_system,
            icon = drawable.ic_system,
            themeMode = ThemeMode.System
        ),
        ThemeModel(
            title = string.settings_light,
            icon = drawable.ic_light,
            themeMode = ThemeMode.Light
        ),
        ThemeModel(
            title = string.settings_dark,
            icon = drawable.ic_dark,
            themeMode = ThemeMode.Dark
        ),
    )

    init {
        _state.update {
            it.copy(themeList = themeList.toPersistentList())
        }
        getThemeMode()
    }

    fun reduce(event: ThemeEvents) {
        when (event) {
            is ThemeEvents.UpdateTheme -> {
                updateTheme(event.themeMode)
                getThemeMode()
            }
        }
    }

    private fun updateTheme(themeMode: ThemeMode) {
        viewModelScope.launch {
            storeRepository.setThemeMode(themeMode)
        }
    }

    private fun getThemeMode() {
        storeRepository.getThemeMode()
            .onEach { themeMode ->
                _state.update { state ->
                    state.copy(currentThemeMode = themeMode)
                }
            }.launchIn(viewModelScope)
    }
}