package uz.toshmatov.bookpro.presentation.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.viewmodel.koinViewModel
import uz.toshmatov.bookpro.core.theme.TopTeacherColors
import uz.toshmatov.bookpro.core.uicomponent.TopBar
import uz.toshmatov.bookpro.core.uicomponent.TopDialog
import uz.toshmatov.bookpro.core.utils.resource
import uz.toshmatov.bookpro.core.utils.string
import uz.toshmatov.bookpro.presentation.screen.auth.signin.VoyagerLoginScreen
import uz.toshmatov.bookpro.presentation.screen.setting.component.SettingItem
import uz.toshmatov.bookpro.presentation.screen.setting.feature.language.LanguageScreen
import uz.toshmatov.bookpro.presentation.screen.setting.feature.theme.ThemeScreen
import uz.toshmatov.bookpro.presentation.screen.setting.intents.SettingsState
import uz.toshmatov.bookpro.presentation.screen.setting.model.ActionType

class VoyagerSettingScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SettingsViewModel>()
        val state by viewModel.state.collectAsState()
        val currentNavigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current
        val showDialog = rememberSaveable { mutableStateOf(false) }

        SettingScreen(
            state = state,
            onClickItem = {
                when (it) {
                    ActionType.LANGUAGE -> currentNavigator.push(LanguageScreen())
                    ActionType.THEME -> currentNavigator.push(ThemeScreen())
                    ActionType.LOGOUT -> {
                        showDialog.value = true
                    }
                }
            },
            backClick = currentNavigator::pop
        )

        if (showDialog.value) {
            TopDialog(
                isLoading = state.isLoading,
                title = string.log_out_title,
                content = string.log_out_confirm.resource,
                confirmText = string.log_out,
                isDelete = true,
                onConfirm = {
                    viewModel.logOutUser()
                },
                onDismiss = { showDialog.value = false },
            )
        }

        if (state.isLogOut) {
            currentNavigator.replaceAll(VoyagerLoginScreen())
        }
    }
}

@Composable
fun SettingScreen(
    state: SettingsState,
    onClickItem: (ActionType) -> Unit,
    backClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TopTeacherColors.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            titleId = string.tab_setting,
            onBackClick = backClick,
            contentDescription = "Back"
        )

        for (setting in state.settings) {
            SettingItem(
                settingModel = setting,
                onClick = onClickItem
            )
        }

        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
    }
}