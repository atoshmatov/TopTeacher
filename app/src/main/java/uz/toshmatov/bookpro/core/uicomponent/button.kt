package uz.toshmatov.bookpro.core.uicomponent

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.toshmatov.bookpro.core.theme.TopTeacherColors
import uz.toshmatov.bookpro.core.theme.TopTeacherDimensions
import uz.toshmatov.bookpro.core.theme.TopTeacherTypography
import uz.toshmatov.bookpro.core.utils.drawable
import uz.toshmatov.bookpro.core.utils.resource
import uz.toshmatov.bookpro.core.utils.string

@Composable
fun CompletedButton(
    modifier: Modifier = Modifier,
    onCompleted: () -> Unit,
    @StringRes textContent: Int = string.login,
    enabled: Boolean = true,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(bottom = TopTeacherDimensions.containerPadding)
            .padding(horizontal = TopTeacherDimensions.medium),
        onClick = onCompleted,
        colors = ButtonColors(
            containerColor = TopTeacherColors.button,
            contentColor = TopTeacherColors.textPrimary,
            disabledContentColor = TopTeacherColors.textPrimary,
            disabledContainerColor = TopTeacherColors.textSecondary,
        ),
        shape = RoundedCornerShape(TopTeacherDimensions.small),
        enabled = enabled
    ) {
        Text(
            textContent.resource,
            style = TopTeacherTypography.textSemiBold,
        )
    }
}

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    @StringRes textContent: Int
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(bottom = TopTeacherDimensions.containerPadding)
            .padding(horizontal = TopTeacherDimensions.medium),
        onClick = onClick,
        colors = ButtonColors(
            containerColor = TopTeacherColors.itemBackground,
            contentColor = TopTeacherColors.text,
            disabledContentColor = TopTeacherColors.button,
            disabledContainerColor = TopTeacherColors.button,
        ),
        shape = RoundedCornerShape(TopTeacherDimensions.small),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CurrencyIcon(
                image = drawable.ic_google,
                size = 32.dp,
                contentDescription = "search"
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                textContent.resource,
                style = TopTeacherTypography.textMedium,
            )
        }
    }
}