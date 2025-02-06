@file:OptIn(ExperimentalMaterial3Api::class)

package uz.toshmatov.bookpro.core.uicomponent

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import uz.toshmatov.bookpro.core.theme.TopTeacherColors
import uz.toshmatov.bookpro.core.theme.TopTeacherDimensions
import uz.toshmatov.bookpro.core.theme.TopTeacherTypography
import uz.toshmatov.bookpro.core.utils.drawable
import uz.toshmatov.bookpro.core.utils.resource

@Composable
fun TopBar(
    @StringRes titleId: Int,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    color: Color = Color.Transparent,
    contentDescription: String,
) {
    TopAppBar(
        modifier = modifier
            .padding(start = TopTeacherDimensions.medium),
        title = {
            Text(
                text = titleId.resource,
                style = TopTeacherTypography.textSemiBold,
                color = TopTeacherColors.button,
                modifier = Modifier.padding(start = TopTeacherDimensions.small),
            )
        },
        navigationIcon = {
            CurrencyIcon(
                image = drawable.ic_arrow_left,
                tint = TopTeacherColors.button,
                onClick = onBackClick,
                contentDescription = contentDescription,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(color),
    )
}
