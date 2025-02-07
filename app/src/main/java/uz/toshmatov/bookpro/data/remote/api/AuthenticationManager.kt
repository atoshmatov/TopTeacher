package uz.toshmatov.bookpro.data.remote.api

import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class AuthenticationManager {
    private val auth = Firebase.auth

    fun loginWithFirebase(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    onResult(true, user?.uid)
                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }

    fun logOut() {
        auth.signOut()
    }
}