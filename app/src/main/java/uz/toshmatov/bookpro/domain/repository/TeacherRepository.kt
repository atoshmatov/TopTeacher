package uz.toshmatov.bookpro.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.toshmatov.bookpro.data.remote.models.Teacher

interface TeacherRepository {
    suspend fun getTeachers(): Flow<List<Teacher>>
    suspend fun updateTeacherRating(
        teacherId: String, newRating: Float, onResult: (Boolean, String?) -> Unit
    )
}