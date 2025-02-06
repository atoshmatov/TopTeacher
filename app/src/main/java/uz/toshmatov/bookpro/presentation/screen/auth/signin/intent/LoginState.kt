package uz.toshmatov.bookpro.presentation.screen.auth.signin.intent

data class LoginState(
    val isLoading: Boolean = false,
    val error: String = "",
    val success: String = "",
    val isLoginSuccess: Boolean = false,
)
