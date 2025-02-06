package uz.toshmatov.bookpro.data.remote.api

import com.google.firebase.Firebase
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import uz.toshmatov.bookpro.core.utils.Resource
import uz.toshmatov.bookpro.core.utils.errorData
import uz.toshmatov.bookpro.core.utils.loading
import uz.toshmatov.bookpro.core.utils.success

class AuthenticationManager {
    private val auth = Firebase.auth

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

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

interface AuthResponse {
    object Success : AuthResponse
    object Loading : AuthResponse
    data class Error(val message: String) : AuthResponse
}