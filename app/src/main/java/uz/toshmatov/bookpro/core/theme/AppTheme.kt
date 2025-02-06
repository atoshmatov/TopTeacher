package uz.toshmatov.bookpro.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import uz.toshmatov.bookpro.core.theme.color.TopTeacherColors
import uz.toshmatov.bookpro.core.theme.color.LocalColors
import uz.toshmatov.bookpro.core.theme.deminsion.TopTeacherDimensions
import uz.toshmatov.bookpro.core.theme.deminsion.LocalDimensions
import uz.toshmatov.bookpro.core.theme.typography.TopTeacherTypography
import uz.toshmatov.bookpro.core.theme.typography.LocalTypography

val TopTeacherColors
    @Composable
    get() = AppTheme.colors

val TopTeacherDimensions
    @Composable
    get() = AppTheme.dimensions

val TopTeacherTypography
    @Composable
    get() = AppTheme.typography

internal object AppTheme {
    val colors: TopTeacherColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val dimensions: TopTeacherDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current

    val typography: TopTeacherTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}
