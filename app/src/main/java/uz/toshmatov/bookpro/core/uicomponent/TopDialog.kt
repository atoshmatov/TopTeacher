package uz.toshmatov.bookpro.core.uicomponent

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uz.toshmatov.bookpro.core.theme.TopTeacherColors
import uz.toshmatov.bookpro.core.theme.TopTeacherTypography
import uz.toshmatov.bookpro.core.utils.resource
import uz.toshmatov.bookpro.core.utils.string

@Composable
fun TopDialog(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    @StringRes title: Int = 0,
    content: String = "",
    @StringRes confirmText: Int = 0,
    isDelete: Boolean = false,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {
            if (!isLoading) {
                onDismiss()
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp))
            .shadow(elevation = 12.dp),
        containerColor = TopTeacherColors.itemBackground,
        title = {
            if (!isLoading) {
                Text(
                    text = title.resource,
                    style = TopTeacherTypography.buttonRegular,
                    color = TopTeacherColors.text,
                )
            }
        },
        text = {
            if (!isLoading) {
                Text(
                    text = content,
                    style = TopTeacherTypography.textMedium,
                    color = TopTeacherColors.text,
                )
            } else {
                LoadingMedium(Modifier.fillMaxWidth())
            }
        },
        confirmButton = {
            if (!isLoading) {
                CommetaTextButton(onClick = onConfirm) {
                    Text(
                        text = confirmText.resource,
                        style = TopTeacherTypography.textMedium,
                        color = if (isDelete) TopTeacherColors.error else TopTeacherColors.icon,
                    )
                }
            }
        },
        dismissButton = {
            if (!isLoading) {
                CommetaTextButton(onClick = onDismiss) {
                    Text(
                        text = string.cancel.resource,
                        style = TopTeacherTypography.textMedium,
                        color = TopTeacherColors.textSecondary,
                    )
                }
            }
        },
    )
}

@Composable
fun CommetaTextButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = Color.Unspecified,
    onClick: () -> Unit = {},
    content: @Composable RowScope.() -> Unit,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = color,
        ),
        content = content,
    )
}
