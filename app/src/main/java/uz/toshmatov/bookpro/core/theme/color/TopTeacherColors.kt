package uz.toshmatov.bookpro.core.theme.color

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

class TopTeacherColors(
    background: Color,
    text: Color,
    textSecondary: Color,
    textPrimary:Color,
    icon: Color,
    iconGray: Color,
    error: Color,
    success:Color,
    button: Color,
    bottomBar: Color,
    bottomBarIcon: Color,
    bottomBarIconSelected: Color,
    bottomBarText: Color,
    bottomBarTextSelected: Color,
    bottomBarIndicator: Color,
    itemBackground: Color,
    shimmer: Color,
    shimmerLight: Color
) {

    var background by mutableStateOf(background)
        private set

    var text by mutableStateOf(text)
        private set

    var textSecondary by mutableStateOf(textSecondary)
        private set

    var textPrimary by mutableStateOf(textPrimary)
        private set

    var icon by mutableStateOf(icon)
        private set

    var iconGray by mutableStateOf(iconGray)
        private set

    var error by mutableStateOf(error)
        private set

    var success by mutableStateOf(success)
        private set

    var button by mutableStateOf(button)
        private set

    var bottomBar by mutableStateOf(bottomBar)
        private set

    var bottomBarIcon by mutableStateOf(bottomBarIcon)
        private set

    var bottomBarIconSelected by mutableStateOf(bottomBarIconSelected)
        private set

    var bottomBarText by mutableStateOf(bottomBarText)
        private set

    var bottomBarTextSelected by mutableStateOf(bottomBarTextSelected)
        private set

    var bottomBarIndicator by mutableStateOf(bottomBarIndicator)
        private set

    var itemBackground by mutableStateOf(itemBackground)
        private set

    var shimmer by mutableStateOf(shimmer)
        private set

    var shimmerLight by mutableStateOf(shimmerLight)
        private set

    fun copy(
        background: Color = this.background,
        text: Color = this.text,
        textSecondary: Color = this.textSecondary,
        textPrimary: Color = this.textPrimary,
        icon: Color = this.icon,
        iconGray: Color = this.iconGray,
        error: Color = this.error,
        success: Color = this.success,
        button: Color = this.button,
        bottomBar: Color = this.bottomBar,
        bottomBarIcon: Color = this.bottomBarIcon,
        bottomBarIconSelected: Color = this.bottomBarIconSelected,
        bottomBarText: Color = this.bottomBarText,
        bottomBarTextSelected: Color = this.bottomBarTextSelected,
        bottomBarIndicator: Color = this.bottomBarIndicator,
        itemBackground: Color = this.itemBackground,
        shimmer: Color = this.shimmer,
        shimmerLight: Color = this.shimmerLight
    ) = TopTeacherColors(
        background = background,
        text = text,
        textSecondary = textSecondary,
        textPrimary = textPrimary,
        icon = icon,
        iconGray = iconGray,
        error = error,
        success = success,
        button = button,
        bottomBar = bottomBar,
        bottomBarIcon = bottomBarIcon,
        bottomBarIconSelected = bottomBarIconSelected,
        bottomBarText = bottomBarText,
        bottomBarTextSelected = bottomBarTextSelected,
        bottomBarIndicator = bottomBarIndicator,
        itemBackground = itemBackground,
        shimmer = shimmer,
        shimmerLight = shimmerLight
    )

    fun updateColorsFrom(other: TopTeacherColors) {
        background = other.background
        text = other.text
        textSecondary = other.textSecondary
        textPrimary = other.textPrimary
        icon = other.icon
        iconGray = other.iconGray
        error = other.error
        success = other.success
        button = other.button
        bottomBar = other.bottomBar
        bottomBarIcon = other.bottomBarIcon
        bottomBarIconSelected = other.bottomBarIconSelected
        bottomBarText = other.bottomBarText
        bottomBarTextSelected = other.bottomBarTextSelected
        bottomBarIndicator = other.bottomBarIndicator
        itemBackground = other.itemBackground
        shimmer = other.shimmer
        shimmerLight = other.shimmerLight
    }
}