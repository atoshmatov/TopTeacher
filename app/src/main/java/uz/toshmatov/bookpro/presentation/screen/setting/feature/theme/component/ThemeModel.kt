package uz.toshmatov.bookpro.presentation.screen.setting.feature.theme.component

import androidx.annotation.StringRes
import uz.toshmatov.bookpro.data.local.model.ThemeMode

data class ThemeModel(
    @StringRes val title: Int,
    val icon: Int,
    val themeMode: ThemeMode
)
