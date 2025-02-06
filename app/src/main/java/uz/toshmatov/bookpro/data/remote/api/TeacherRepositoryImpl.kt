package uz.toshmatov.bookpro.data.remote.api

class TeacherRepository {

    suspend fun getTeachers(): List<Teacher> {
        val firestore = FirebaseFirestore.getInstance()
        return try {
            val snapshot = firestore.collection("teachers").get().await()
            snapshot.toObjects<Teacher>()
        } catch (e: Exception) {
            emptyList()
        }
    }
}