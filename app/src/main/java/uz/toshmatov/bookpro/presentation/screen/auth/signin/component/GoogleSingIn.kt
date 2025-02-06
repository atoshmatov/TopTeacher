package uz.toshmatov.bookpro.presentation.screen.auth.signin.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.toshmatov.bookpro.core.theme.TopTeacherColors
import uz.toshmatov.bookpro.core.theme.TopTeacherTypography
import uz.toshmatov.bookpro.core.uicomponent.GoogleButton
import uz.toshmatov.bookpro.core.utils.resource
import uz.toshmatov.bookpro.core.utils.string

@Composable
fun GoogleSignInItem(modifier: Modifier = Modifier, onCompleted: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            string.or_with_google.resource,
            style = TopTeacherTypography.captionUppercase,
            color = TopTeacherColors.text
        )

        Spacer(modifier = Modifier.height(20.dp))
        GoogleButton(
            onClick = onCompleted,
            textContent = string.google_text
        )
    }
}