package uz.toshmatov.bookpro.presentation.screen.setting

import androidx.lifecycle.ViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import uz.toshmatov.bookpro.core.utils.drawable
import uz.toshmatov.bookpro.core.utils.string
import uz.toshmatov.bookpro.data.local.datastore.OnboardingUtils
import uz.toshmatov.bookpro.data.remote.api.AuthenticationManager
import uz.toshmatov.bookpro.presentation.screen.setting.intents.SettingsState
import uz.toshmatov.bookpro.presentation.screen.setting.model.ActionType
import uz.toshmatov.bookpro.presentation.screen.setting.model.SettingModel

class SettingsViewModel(
    private val authUser: AuthenticationManager,
    private val pref: OnboardingUtils
) : ViewModel() {

    private val _state: MutableStateFlow<SettingsState> = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state.asStateFlow()

    private val settings = listOf(
        SettingModel(
            string.settings_language,
            drawable.ic_language,
            ActionType.LANGUAGE
        ),
        SettingModel(
            string.settings_theme,
            drawable.ic_style,
            ActionType.THEME
        ),
        SettingModel(
            string.log_out,
            drawable.ic_logout,
            ActionType.LOGOUT,
            isLogOut = true,
            isActivated = true
        ),
    )

    init {
        _state.update {
            it.copy(settings = settings.toPersistentList())
        }
    }

    fun logOutUser() {
        authUser.logOut()
        pref.setLogout()
        _state.update {
            it.copy(isLogOut = true)
        }
    }
}