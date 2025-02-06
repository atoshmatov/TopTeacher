package uz.toshmatov.bookpro.presentation.screen.auth.signin.component

import android.os.Build
import android.view.autofill.AutofillManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import uz.toshmatov.bookpro.core.theme.TopTeacherColors
import uz.toshmatov.bookpro.core.theme.TopTeacherDimensions
import uz.toshmatov.bookpro.core.theme.TopTeacherTypography
import uz.toshmatov.bookpro.core.uicomponent.CompletedButton
import uz.toshmatov.bookpro.core.uicomponent.TopTeacherTextField
import uz.toshmatov.bookpro.core.utils.drawable
import uz.toshmatov.bookpro.core.utils.resource
import uz.toshmatov.bookpro.core.utils.string

@Composable
fun InputComponent(
    modifier: Modifier = Modifier,
    onCompleted: () -> Unit,
    onEmail: (String) -> Unit,
    onPassword: (String) -> Unit,
    buttonEnabled: Boolean = true,
    isErrorEmail: Boolean = false,
    isErrorPassword: Boolean = false
) {
    val scroll = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scroll),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopTeacherTextField(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
                .background(TopTeacherColors.background)
                .padding(
                    horizontal = TopTeacherDimensions.medium,
                    vertical = TopTeacherDimensions.small
                ),
            icon = drawable.ic_email,
            onValueChange = onEmail,
            isError = isErrorEmail
        )

        TopTeacherTextField(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
                .background(TopTeacherColors.background)
                .padding(
                    horizontal = TopTeacherDimensions.medium,
                    vertical = TopTeacherDimensions.small
                ),
            icon = drawable.ic_parol,
            placeHolderResId = string.password,
            onValueChange = onPassword,
            isError = isErrorPassword
        )

        Spacer(modifier = Modifier.height(20.dp))

        CompletedButton(
            textContent = string.login,
            onCompleted = onCompleted,
            enabled = buttonEnabled
        )

        /*Spacer(modifier = Modifier.height(20.dp))
        Text(
            string.create_an_account.resource,
            modifier = Modifier.clickable { },
            style = TopTeacherTypography.captionUppercase,
            color = TopTeacherColors.button
        )*/
    }
}