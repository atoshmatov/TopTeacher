package uz.toshmatov.bookpro.presentation.screen.setting.feature.language.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import uz.toshmatov.bookpro.presentation.screen.setting.feature.language.component.LangType

data class LanguageModel(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val langType: LangType,
    val code: String,
)
