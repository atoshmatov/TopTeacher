package uz.toshmatov.bookpro.data.remote.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Teacher(
    val teacher_id: String? = "",
    val name: String? = "",
    val subject: String? = "",
    val university: String? = "",
    val rating: Float? = null,
    val ratingCount: Float? = null
) : Parcelable
