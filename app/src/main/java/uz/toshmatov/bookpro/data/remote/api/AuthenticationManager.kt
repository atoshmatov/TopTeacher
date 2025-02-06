package uz.toshmatov.bookpro.data.remote.api

import com.google.firebase.Firebase
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.auth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.toshmatov.bookpro.core.utils.Resource
import uz.toshmatov.bookpro.core.utils.errorData
import uz.toshmatov.bookpro.core.utils.loading
import uz.toshmatov.bookpro.core.utils.success

class AuthenticationManager {
    private val auth = Firebase.auth

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
    ): Flow<AuthResponse> = callbackFlow {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    trySend(AuthResponse.Success)
                } else {
                    val errorMessage = when (val exception = task.exception) {
                        is FirebaseAuthUserCollisionException -> "This email is already in use"
                        else -> exception?.message ?: "Unknown error"
                    }
                    trySend(AuthResponse.Error(message = errorMessage))
                }
            }
        awaitClose()
    }

    fun loginWithEmail(
        email: String,
        password: String,
    ): Flow<AuthResponse> = callbackFlow {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    trySend(AuthResponse.Success)
                } else {
                    val errorMessage = when (val exception = task.exception) {
                        is FirebaseAuthInvalidUserException -> "Bunday foydalanuvchi topilmadi"
                        is FirebaseAuthInvalidCredentialsException -> "Noto‘g‘ri parol yoki email"
                        is FirebaseNetworkException -> "Internet bilan bog‘liq muammo"
                        else -> exception?.message ?: "Noma'lum xatolik"
                    }
                    trySend(AuthResponse.Error(message = errorMessage))
                }
            }
        awaitClose()
    }

}

interface AuthResponse {
    object Success : AuthResponse
    object Loading : AuthResponse
    data class Error(val message: String) : AuthResponse
}

/*private fun createNonce(): String {
    val rawNonce = UUID.randomUUID().toString()
    val bytes = rawNonce.toByteArray()

    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)

    return digest.fold("") { str, it ->
        str + "%02x".format(it)
    }
}*/

/*fun signInWithGoogle(): Flow<AuthResponse> = callbackFlow {
       try {
           val nonce = createNonce()
           Log.d("GoogleSignIn", "Generated nonce: $nonce")

           val googleIdOption = GetGoogleIdOption.Builder()
               .setFilterByAuthorizedAccounts(false)
               .setServerClientId(context.getString(string.web_client_id))
               .setAutoSelectEnabled(false)
               .setNonce(nonce)
               .build()

           val request = GetCredentialRequest.Builder()
               .addCredentialOption(googleIdOption)
               .build()

           val credentialManager = CredentialManager.create(context)
           val result = credentialManager.getCredential(context, request)
           val credential = result.credential

           Log.d("GoogleSignIn", "Credential received: $credential")

           if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
               try {
                   val googleIdTokenCredential =
                       GoogleIdTokenCredential.createFrom(credential.data)

                   Log.d("GoogleSignIn", "Google ID Token: ${googleIdTokenCredential.idToken}")

                   if (googleIdTokenCredential.idToken == null) {
                       trySend(AuthResponse.Error(message = "Google ID token is null"))
                       return@callbackFlow
                   }

                   val firebaseCredential =
                       GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)

                   auth.signInWithCredential(firebaseCredential).addOnCompleteListener { task ->
                       if (task.isSuccessful) {
                           Log.d("GoogleSignIn", "Firebase sign-in successful")
                           trySend(AuthResponse.Success)
                       } else {
                           Log.e("GoogleSignIn", "Firebase sign-in failed", task.exception)
                           trySend(
                               AuthResponse.Error(
                                   message = task.exception?.message ?: "Firebase sign-in error"
                               )
                           )
                       }
                   }
               } catch (e: GoogleIdTokenParsingException) {
                   Log.e("GoogleSignIn", "Google ID Token parsing error", e)
                   trySend(
                       AuthResponse.Error(
                           message = e.message ?: "Google ID Token parsing error"
                       )
                   )
               }
           } else {
               Log.e("GoogleSignIn", "Invalid credential type")
               trySend(AuthResponse.Error(message = "Invalid credential type"))
           }
       } catch (e: Exception) {
           Log.e("GoogleSignIn", "Exception in signInWithGoogle", e)
           trySend(AuthResponse.Error(message = e.message ?: "Unknown error"))
       }

       awaitClose()
   }*/
/*fun signInWithGoogle(): Flow<AuthResponse> = callbackFlow {
    val googleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false)
        .setServerClientId(context.getString(string.web_client_id))
        .setAutoSelectEnabled(false)
        .setNonce(createNonce())
        .build()

    val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()

    try {
        val credentialManager = CredentialManager.create(context)
        val result = credentialManager.getCredential(
            context = context,
            request = request
        )

        val credential = result.credential
        if (credential is CustomCredential) {
            if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                try {
                    val googleIdTokenCredential =
                        GoogleIdTokenCredential.createFrom(credential.data)

                    val firebaseCredential = GoogleAuthProvider.getCredential(
                        googleIdTokenCredential.idToken,
                        null
                    )

                    auth.signInWithCredential(firebaseCredential).addOnCompleteListener {
                        if (it.isSuccessful) {
                            trySend(AuthResponse.Success)
                        } else {
                            trySend(AuthResponse.Error(message = it.exception?.message ?: ""))
                        }
                    }
                } catch (e: GoogleIdTokenParsingException) {
                    trySend(AuthResponse.Error(message = e.message ?: ""))
                }
            }
        }
    } catch (e: Exception) {
        trySend(AuthResponse.Error(message = e.message ?: ""))
    }
    awaitClose()
}*/