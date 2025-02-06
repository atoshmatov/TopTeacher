package uz.toshmatov.bookpro.core.uicomponent

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.toshmatov.bookpro.core.theme.TopTeacherColors
import uz.toshmatov.bookpro.core.theme.TopTeacherDimensions
import uz.toshmatov.bookpro.core.theme.TopTeacherTypography
import uz.toshmatov.bookpro.core.utils.drawable
import uz.toshmatov.bookpro.core.utils.resource
import uz.toshmatov.bookpro.core.utils.string

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopTeacherTextField(
    modifier: Modifier = Modifier,
    @StringRes placeHolderResId: Int = string.email,
    onValueChange: (String) -> Unit,
    @DrawableRes icon: Int = drawable.ic_email,
    onFocus: () -> Unit = {},
    isError: Boolean = false
) {
    var text by rememberSaveable { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(text)
        },
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(TopTeacherColors.bottomBar)
            .onFocusChanged { focusState ->
                if (focusState.isFocused) {
                    onFocus()
                }
            },
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            color = TopTeacherColors.text,
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
        ),
        interactionSource = interactionSource,
        cursorBrush = SolidColor(TopTeacherColors.text),
    ) { innerTextField ->
        TextFieldDefaults.DecorationBox(
            value = text,
            innerTextField = innerTextField,
            placeholder = {
                Text(
                    text = placeHolderResId.resource,
                    color = TopTeacherColors.textSecondary,
                    style = TopTeacherTypography.textMedium,
                )
            },
            enabled = true,
            singleLine = true,
            isError = isError,
            colors = TextFieldDefaults.colors(
                focusedLeadingIconColor = TopTeacherColors.icon,
                unfocusedLeadingIconColor = TopTeacherColors.icon,
                focusedContainerColor = TopTeacherColors.bottomBar,
                unfocusedContainerColor = TopTeacherColors.bottomBar,
                focusedIndicatorColor = TopTeacherColors.bottomBar,
                unfocusedIndicatorColor = TopTeacherColors.bottomBar,
                cursorColor = TopTeacherColors.text,
                focusedTextColor = TopTeacherColors.text,
                unfocusedTextColor = TopTeacherColors.text,
                disabledTextColor = TopTeacherColors.textSecondary,
            ),
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            leadingIcon = {
                CurrencyIcon(
                    image = icon,
                    size = 20.dp,
                    contentDescription = "search",
                    tint = TopTeacherColors.iconGray
                )
            },
            trailingIcon = {
                AnimatedVisibility(
                    visible = text.isNotBlank(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    CurrencyIcon(
                        image = drawable.ic_close,
                        size = 16.dp,
                        contentDescription = "search",
                        tint = TopTeacherColors.textSecondary,
                        onClick = {
                            text = ""
                            onValueChange(text)
                        }
                    )
                }
            },
            contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                top = TopTeacherDimensions.empty,
                bottom = TopTeacherDimensions.empty,
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearTextField(
    modifier: Modifier = Modifier,
    @StringRes placeHolderResId: Int = string.home_search,
    onValueChange: (String) -> Unit,
    onFocus: () -> Unit = {},
) {
    var text by rememberSaveable { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(text)
        },
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(TopTeacherColors.bottomBar)
            .onFocusChanged { focusState ->
                if (focusState.isFocused) {
                    onFocus()
                }
            },
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            color = TopTeacherColors.text,
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
        ),
        interactionSource = interactionSource,
        cursorBrush = SolidColor(TopTeacherColors.text)
    ) { innerTextField ->
        TextFieldDefaults.DecorationBox(
            value = text,
            innerTextField = innerTextField,
            placeholder = {
                Text(
                    text = placeHolderResId.resource,
                    color = TopTeacherColors.textSecondary,
                    style = TopTeacherTypography.textMedium,
                )
            },
            enabled = true,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedLeadingIconColor = TopTeacherColors.icon,
                unfocusedLeadingIconColor = TopTeacherColors.icon,
                focusedContainerColor = TopTeacherColors.bottomBar,
                unfocusedContainerColor = TopTeacherColors.bottomBar,
                focusedIndicatorColor = TopTeacherColors.bottomBar,
                unfocusedIndicatorColor = TopTeacherColors.bottomBar,
                cursorColor = TopTeacherColors.text,
                focusedTextColor = TopTeacherColors.text,
                unfocusedTextColor = TopTeacherColors.text,
                disabledTextColor = TopTeacherColors.textSecondary
            ),
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            leadingIcon = {
                CurrencyIcon(
                    image = drawable.ic_search,
                    size = 16.dp,
                    contentDescription = "search"
                )
            },
            trailingIcon = {
                AnimatedVisibility(
                    visible = text.isNotBlank(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    CurrencyIcon(
                        image = drawable.ic_close,
                        size = 16.dp,
                        contentDescription = "search",
                        tint = TopTeacherColors.textSecondary,
                        onClick = {
                            text = ""
                            onValueChange(text)
                        }
                    )
                }
            },
            contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
                top = TopTeacherDimensions.empty,
                bottom = TopTeacherDimensions.empty,
            ),
        )
    }
}