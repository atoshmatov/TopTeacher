package uz.toshmatov.bookpro.presentation.screen.auth.signin.intent

interface LoginEvent {
    data class CreateUser(val email: String, val password: String) : LoginEvent
}