package uz.toshmatov.bookpro.presentation.screen.setting.intents

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import uz.toshmatov.bookpro.presentation.screen.setting.model.SettingModel

data class SettingsState(
    val isLoading: Boolean = false,
    val settings: ImmutableList<SettingModel> = persistentListOf(),
    val isLogOut: Boolean = false,
)