package uz.toshmatov.bookpro.core.extensions

import android.app.Activity
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.core.view.WindowCompat
import uz.toshmatov.bookpro.core.theme.TopTeacherColors

@Composable
fun StatusBarStyle(
    statusBarColor: Color = TopTeacherColors.bottomBar,
    navigationBarColor: Color = TopTeacherColors.bottomBar
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = statusBarColor.toArgb()
            window.navigationBarColor = navigationBarColor.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }
}

fun Modifier.iconIndication(radius: Dp) = composed {
    val interactionSource = remember { MutableInteractionSource() }

    indication(
        interactionSource = interactionSource,
        indication = rememberRipple(
            color = TopTeacherColors.bottomBar,
            radius = radius
        )
    )
}

