package uz.toshmatov.bookpro.presentation.screen.main

interface MainEvent {
    data class SearchQuery(val query: String) : MainEvent
    data class UpdateTeacherRating(val teacherId: String, val newRating: Float) : MainEvent
}