package uz.toshmatov.bookpro.presentation.screen.main.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uz.toshmatov.bookpro.core.theme.TopTeacherColors
import uz.toshmatov.bookpro.core.theme.TopTeacherTypography
import uz.toshmatov.bookpro.core.utils.drawable

@Composable
fun RatingBar(rating: Float) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "%.1f".format(rating),
            style = TopTeacherTypography.textMedium,
            color = TopTeacherColors.text,
            modifier = Modifier.padding(end = 4.dp)
        )
        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = "Rating Star",
            tint = TopTeacherColors.success,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun RatingBarUpdate(
    currentRating: Float,
    onRatingChanged: (Float) -> Unit
) {
    var selectedRating by remember { mutableStateOf(currentRating) }

    Row(modifier = Modifier.padding(8.dp)) {
        for (i in 1..5) {
            Icon(
                imageVector = if (i <= selectedRating.toInt()) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = "Star $i",
                tint = Color.Yellow,
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        selectedRating = i.toFloat()
                        onRatingChanged(selectedRating)
                    }
            )
        }
    }
}

@Composable
fun RatingDialog(
    currentRating: Float,
    onDismiss: () -> Unit,
    onRatingConfirmed: (Float) -> Unit
) {
    var selectedRating by remember { mutableFloatStateOf(currentRating) }

    AlertDialog(
        modifier = Modifier.fillMaxWidth(),
        containerColor = TopTeacherColors.itemBackground,
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                "O'qituvchini baholang",
                color = TopTeacherColors.text,
                style = TopTeacherTypography.buttonCalculator
            )
        },
        text = {
            Row(modifier = Modifier.padding(8.dp)) {
                for (i in 1..5) {
                    Icon(
                        painter = if (i <= selectedRating.toInt()) painterResource(drawable.ic_star_fill) else painterResource(
                            drawable.ic_star_line
                        ),
                        contentDescription = "Star $i",
                        tint = TopTeacherColors.success,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                selectedRating = i.toFloat()
                            }
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onRatingConfirmed(selectedRating)
                    onDismiss()
                },
                colors = ButtonColors(
                    containerColor = TopTeacherColors.button,
                    contentColor = TopTeacherColors.textPrimary,
                    disabledContainerColor = TopTeacherColors.button,
                    disabledContentColor = TopTeacherColors.textPrimary,
                ),
            ) {
                Text("Saqlash")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Bekor qilish", color = TopTeacherColors.text)
            }
        }
    )
}



