package uz.toshmatov.bookpro.core.extensions

import com.google.accompanist.systemuicontroller.SystemUiController
import uz.toshmatov.bookpro.core.theme.color.BLACK
import uz.toshmatov.bookpro.core.theme.color.DARK
import uz.toshmatov.bookpro.core.theme.color.GRAY10
import uz.toshmatov.bookpro.core.theme.color.WHITE

internal fun SystemUiController.applyTheme(isDarkTheme: Boolean) {
    val statusBarColor = if (isDarkTheme) BLACK else GRAY10
    val navigationBarColor = if (isDarkTheme) DARK else GRAY10

    setSystemBarsColor(
        color = statusBarColor,
    )
    setNavigationBarColor(
        color = navigationBarColor,
    )
}
