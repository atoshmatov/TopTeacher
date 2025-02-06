package uz.toshmatov.bookpro.presentation.screen.setting.feature.theme.intents

import uz.toshmatov.bookpro.data.local.model.ThemeMode

sealed interface ThemeEvents {
    data class UpdateTheme(val themeMode: ThemeMode) : ThemeEvents
}