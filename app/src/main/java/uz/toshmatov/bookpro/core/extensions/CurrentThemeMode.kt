package uz.toshmatov.bookpro.core.extensions

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import uz.toshmatov.bookpro.data.local.model.ThemeMode

@Composable
fun getCurrentThemeMode(themeMode: ThemeMode): Boolean {
    return when (themeMode) {
        ThemeMode.Dark -> true
        ThemeMode.Light -> false
        ThemeMode.System -> isSystemInDarkTheme()
    }
}