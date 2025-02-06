package uz.toshmatov.bookpro.core.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun String.formatDate(): String {
    val inputFormat = "MM/dd/yyyy"
    val outputFormat = "dd-MM-yyyy"
    val inputFormatter = DateTimeFormatter.ofPattern(inputFormat)
    val outputFormatter = DateTimeFormatter.ofPattern(outputFormat)

    val date = LocalDate.parse(this, inputFormatter)
    return date.format(outputFormatter)
}