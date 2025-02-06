package uz.toshmatov.bookpro.core.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import uz.toshmatov.bookpro.core.extensions.StatusBarStyle
import uz.toshmatov.bookpro.core.extensions.applyTheme
import uz.toshmatov.bookpro.core.theme.color.TopTeacherColors
import uz.toshmatov.bookpro.core.theme.color.LocalColors
import uz.toshmatov.bookpro.core.theme.color.darkColors
import uz.toshmatov.bookpro.core.theme.color.lightColors
import uz.toshmatov.bookpro.core.theme.deminsion.TopTeacherDimensions
import uz.toshmatov.bookpro.core.theme.deminsion.LocalDimensions
import uz.toshmatov.bookpro.core.theme.typography.TopTeacherTypography
import uz.toshmatov.bookpro.core.theme.typography.LocalTypography

@Composable
fun TopTeacherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    typography: TopTeacherTypography = TopTeacherTypography,
    dimensions: TopTeacherDimensions = TopTeacherDimensions,
    lightStatusBar: Boolean = !darkTheme,
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val colors: TopTeacherColors = if (darkTheme) darkColors() else lightColors()
    val rippleIndication = rememberRipple()
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }

    StatusBarStyle(
        statusBarColor = colors.background,
        navigationBarColor = colors.background
    )

    SideEffect {
        systemUiController.applyTheme(darkTheme && !lightStatusBar)
    }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalDimensions provides dimensions,
        LocalTypography provides typography,
        LocalIndication provides rippleIndication
    ) {
        MaterialTheme {
            content()
        }
    }
}