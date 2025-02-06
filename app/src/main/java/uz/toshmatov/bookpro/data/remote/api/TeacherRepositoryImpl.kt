package uz.toshmatov.bookpro.data.remote.api

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.toshmatov.bookpro.data.remote.models.Teacher
import uz.toshmatov.bookpro.domain.repository.TeacherRepository

class TeacherRepositoryImpl : TeacherRepository {
    private val firestore = Firebase.firestore
    private val teachers = firestore.collection("teachers")

    override suspend fun getTeachers(): Flow<List<Teacher>> = callbackFlow {
        val listener = teachers
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val teachersList =
                    snapshot?.documents?.mapNotNull { it.toObject(Teacher::class.java) }
                        ?: emptyList()
                trySend(teachersList).isSuccess
            }

        awaitClose { listener.remove() }
        /* teachers
             .get()
             .addOnSuccessListener { querySnapshot ->
                 val response = querySnapshot.map { queryDocumentSnapshot ->
                     queryDocumentSnapshot.toObject(Teacher::class.java)
                 }
                 trySend(response)
             }
         awaitClose()*/
    }

    override suspend fun updateTeacherRating(
        teacherId: String,
        newRating: Float,
        onResult: (Boolean, String?) -> Unit
    ) {
        val teacherRef = teachers
            .document("teacher_$teacherId")

        firestore.runTransaction { transaction ->
            val snapshot = transaction.get(teacherRef)

            val oldRating = snapshot.getDouble("rating") ?: 0.0
            val ratingCount = snapshot.getLong("rating_count") ?: 0

            val newRatingCount = ratingCount + 1
            val updatedRating = (oldRating * ratingCount + newRating) / newRatingCount

            transaction.update(
                teacherRef, mapOf(
                    "rating" to updatedRating,
                    "rating_count" to newRatingCount
                )
            )
        }.addOnSuccessListener {
            onResult(true, "Rating updated successfully")
        }.addOnFailureListener { e ->
            onResult(false, e.message)
        }
    }

}