package uz.toshmatov.bookpro.presentation.screen.auth.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import io.ktor.client.plugins.auth.Auth
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.toshmatov.bookpro.core.logger.logInfo
import uz.toshmatov.bookpro.core.utils.Resource
import uz.toshmatov.bookpro.core.utils.empty
import uz.toshmatov.bookpro.core.utils.errorData
import uz.toshmatov.bookpro.core.utils.loading
import uz.toshmatov.bookpro.core.utils.success
import uz.toshmatov.bookpro.data.local.datastore.OnboardingUtils
import uz.toshmatov.bookpro.data.remote.api.AuthRepository
import uz.toshmatov.bookpro.data.remote.api.AuthResponse
import uz.toshmatov.bookpro.data.remote.api.AuthenticationManager
import uz.toshmatov.bookpro.presentation.screen.auth.signin.intent.LoginEvent
import uz.toshmatov.bookpro.presentation.screen.auth.signin.intent.LoginState

class LoginViewModel(
    private val authenticationManager: AuthenticationManager,
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun reduce(event: LoginEvent) {
        when (event) {
            is LoginEvent.CreateUser -> {
                login(event.email, event.password)
            }
        }
    }

    fun isLoggedIn(): Boolean =
        authenticationManager.isLoggedIn()


    private fun signIn(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        authenticationManager.loginWithEmail(email, password)
            .onEach { response ->
                //logInfo { "response --> $response" }
                if (response is AuthResponse.Success) {
                    logInfo { "Success --> $response $email $password" }
                    updateLoadingState(isLoading = false)
                    updateIsLoginState(isLogin = true)
                    updateSuccessState(success = "Muvaffaqiyatli kirdingiz!")
                } else {
                    val error = response is AuthResponse.Error
                    logInfo { "Error --> $response $error" }
                    updateErrorState(error = error.toString())
                }
                /*when (response) {
                    is AuthResponse.Loading -> {
                        updateLoadingState(isLoading = true)
                        updateIsLoginState(isLogin = false)
                    }

                    is AuthResponse.Success -> {
                        updateLoadingState(isLoading = false)
                        updateIsLoginState(isLogin = true)
                        updateSuccessState(success = "Muvaffaqiyatli kirdingiz!")
                    }

                    is AuthResponse.Error -> {
                        updateIsLoginState(isLogin = false)
                        updateLoadingState(isLoading = false)
                        updateErrorState(error = response.message)
                    }
                }*/
            }.launchIn(viewModelScope)
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