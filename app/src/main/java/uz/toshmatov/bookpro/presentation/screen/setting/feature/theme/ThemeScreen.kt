package uz.toshmatov.bookpro.presentation.screen.setting.feature.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.androidx.compose.koinViewModel
import uz.toshmatov.bookpro.core.theme.TopTeacherColors
import uz.toshmatov.bookpro.core.uicomponent.TopBar
import uz.toshmatov.bookpro.core.utils.string
import uz.toshmatov.bookpro.presentation.screen.setting.feature.theme.component.ThemeItem
import uz.toshmatov.bookpro.presentation.screen.setting.feature.theme.intents.ThemeEvents
import uz.toshmatov.bookpro.presentation.screen.setting.feature.theme.intents.ThemeState

class ThemeScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<ThemeViewModel>()
        val state by viewModel.state.collectAsState()

        ThemeContent(
            state = state,
            reduce = viewModel::reduce,
            backClick = navigator::pop
        )
    }
}

@Composable
fun ThemeContent(
    state: ThemeState,
    reduce: (ThemeEvents) -> Unit,
    modifier: Modifier = Modifier,
    backClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(TopTeacherColors.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            titleId = string.settings_theme,
            onBackClick = backClick,
            contentDescription = "Back"
        )
        state.themeList.forEach {
            ThemeItem(
                themeModel = it,
                onClick = { mode ->
                    reduce(ThemeEvents.UpdateTheme(mode))
                },
                isCheck = state.currentThemeMode == it.themeMode
            )
        }
    }
}