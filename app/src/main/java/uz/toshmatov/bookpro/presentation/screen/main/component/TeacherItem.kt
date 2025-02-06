package uz.toshmatov.bookpro.presentation.screen.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import uz.toshmatov.bookpro.core.theme.TopTeacherColors
import uz.toshmatov.bookpro.core.theme.TopTeacherTypography
import uz.toshmatov.bookpro.data.remote.models.Teacher
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

@Composable
fun TeacherItem(
    teacher: Teacher,
    itemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = TopTeacherColors.bottomBar)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { itemClick() }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(randomColor().copy(alpha = .1f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = teacher.name?.first().toString(),
                    style = TopTeacherTypography.buttonRegular,
                    color = TopTeacherColors.icon
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = teacher.name ?: "",
                    style = TopTeacherTypography.buttonRegular,
                    color = TopTeacherColors.text
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = teacher.subject ?: "",
                    style = TopTeacherTypography.labelSemiBold,
                    color = TopTeacherColors.textSecondary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = teacher.university ?: "",
                    style = TopTeacherTypography.textMedium,
                    color = TopTeacherColors.textSecondary
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            teacher.rating?.let { RatingBar(it) }
        }
    }
}

fun randomColor(): Color {
    return Color(
        red = Random.nextFloat(),
        green = Random.nextFloat(),
        blue = Random.nextFloat(),
        alpha = 1f
    )
}