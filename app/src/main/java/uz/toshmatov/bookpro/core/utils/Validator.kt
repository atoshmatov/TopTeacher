package uz.toshmatov.bookpro.core.utils

object Validator {
    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}\$"
        return password.matches(passwordPattern.toRegex())
    }
}