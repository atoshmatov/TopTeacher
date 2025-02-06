package uz.toshmatov.bookpro.core.uicomponent

import android.content.Context
import android.widget.Toast

fun Context.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}