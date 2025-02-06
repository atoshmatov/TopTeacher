package uz.toshmatov.bookpro.presentation.screen.setting.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SettingModel(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val actionType: ActionType,
    val isActivated: Boolean = false,
    val isLogOut: Boolean = false
)