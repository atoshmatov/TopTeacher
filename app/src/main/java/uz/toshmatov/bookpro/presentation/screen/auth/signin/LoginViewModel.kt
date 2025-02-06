package uz.toshmatov.bookpro.presentation.screen.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.toshmatov.bookpro.data.local.datastore.OnboardingUtils
import uz.toshmatov.bookpro.data.remote.api.AuthenticationManager
import uz.toshmatov.bookpro.presentation.screen.auth.signin.intent.LoginEvent
import uz.toshmatov.bookpro.presentation.screen.auth.signin.intent.LoginState

class LoginViewModel(
    private val authenticationManager: AuthenticationManager,
    private val pref: OnboardingUtils
) : ViewModel() {
    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun reduce(event: LoginEvent) {
        when (event) {
            is LoginEvent.CreateUser -> {
                loginWithFirebase(event.email, event.password)
            }
        }
    }

    private fun loginWithFirebase(
        email: String,
        password: String,
    ) {
        updateLoadingState(isLoading = true)
        viewModelScope.launch {
            authenticationManager.loginWithFirebase(email, password) { success, studentId ->
                if (success) {
                    updateLoadingState(isLoading = false)
                    pref.setLogin()
                    updateIsLoginState(isLogin = true)
                } else {
                    updateLoadingState(isLoading = false)
                    updateErrorState(error = "$studentId")
                }
            }
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        _state.update {
            it.copy(isLoading = isLoading)
        }
    }

    private fun updateErrorState(error: String) {
        _state.update {
            it.copy(error = error)
        }
    }

    private fun updateSuccessState(success: String) {
        _state.update {
            it.copy(success = success)
        }
    }

    private fun updateIsLoginState(isLogin: Boolean) {
        _state.update {
            it.copy(isLoginSuccess = isLogin)
        }
    }

    private fun updateState(update: (LoginState) -> LoginState) {
        _state.update(update)
    }
}