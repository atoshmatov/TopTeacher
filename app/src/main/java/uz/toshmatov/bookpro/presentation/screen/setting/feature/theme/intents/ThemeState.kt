package uz.toshmatov.bookpro.presentation.screen.setting.feature.theme.intents

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import uz.toshmatov.bookpro.data.local.model.ThemeMode
import uz.toshmatov.bookpro.presentation.screen.setting.feature.theme.component.ThemeModel

data class ThemeState(
    val isLoading: Boolean = false,
    val themeList: ImmutableList<ThemeModel> = persistentListOf(),
    val currentThemeMode: ThemeMode = ThemeMode.System,
)
