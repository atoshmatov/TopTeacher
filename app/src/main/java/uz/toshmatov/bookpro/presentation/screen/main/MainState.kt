package uz.toshmatov.bookpro.presentation.screen.main

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import uz.toshmatov.bookpro.data.remote.models.Teacher

data class MainState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val teachers: ImmutableList<Teacher> = persistentListOf(),
    val searchQuery: String = "",
    val updateTeacherRating: Boolean = false,
    val updateTeacherRatingMessage: String = ""
)
