package uz.toshmatov.bookpro.presentation.screen.auth.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.androidx.compose.koinViewModel
import uz.toshmatov.bookpro.core.image.AnimatedPreloader
import uz.toshmatov.bookpro.core.logger.logInfo
import uz.toshmatov.bookpro.core.theme.TopTeacherColors
import uz.toshmatov.bookpro.core.theme.TopTeacherDimensions
import uz.toshmatov.bookpro.core.uicomponent.LoadingScreen
import uz.toshmatov.bookpro.core.uicomponent.makeToast
import uz.toshmatov.bookpro.core.utils.Validator
import uz.toshmatov.bookpro.core.utils.raw
import uz.toshmatov.bookpro.data.local.datastore.OnboardingUtils
import uz.toshmatov.bookpro.presentation.screen.auth.signin.component.InputComponent
import uz.toshmatov.bookpro.presentation.screen.auth.signin.intent.LoginEvent
import uz.toshmatov.bookpro.presentation.screen.auth.signin.intent.LoginState
import uz.toshmatov.bookpro.presentation.screen.main.VoyagerMainScreen

class VoyagerLoginScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<LoginViewModel>()
        val state = viewModel.state.collectAsState().value

        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current

        val onboardingUtils by lazy { OnboardingUtils(context) }

        LaunchedEffect(Unit) {
            context.makeToast(state.error)
        }

        if (state.isLoading) {
            LoadingScreen()
        } else {
            LoginScreenContent(
                state = state,
                reduce = viewModel::reduce,
                openMainScreen = {
                    if (viewModel.isLoggedIn()) {
                        onboardingUtils.setLogin()
                        navigator.push(VoyagerMainScreen())
                    }
                }
            )
        }

    }
}

@Composable
fun LoginScreenContent(
    state: LoginState,
    modifier: Modifier = Modifier,
    reduce: (LoginEvent) -> Unit,
    openMainScreen: () -> Unit
) {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(TopTeacherColors.background)
            .imePadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))

        AnimatedPreloader(
            modifier = Modifier.size(220.dp),
            lottie = raw.sigin_animation
        )

        Spacer(modifier = Modifier.height(TopTeacherDimensions.large))

        InputComponent(
            onCompleted = {
                if (Validator.isValidEmail(email) && Validator.isValidPassword(password)) {
                    reduce(LoginEvent.CreateUser(email, password))
                    openMainScreen()
                }
            },
            onEmail = { onEmail ->
                email = onEmail
                emailError = if (!Validator.isValidEmail(onEmail)) "Noto‘g‘ri email!" else null
            },
            onPassword = { onPassword ->
                password = onPassword
                passwordError =
                    if (!Validator.isValidPassword(onPassword)) "Parol noto‘g‘ri formatda!" else null
            },
            buttonEnabled = Validator.isValidEmail(email) && Validator.isValidPassword(password),
            isErrorEmail = emailError != null && email.isNotEmpty(),
            isErrorPassword = passwordError != null && password.isNotEmpty()
        )

        /*GoogleSignIn(
            modifier = Modifier.weight(1f),
            onCompleted = {
                auth.signInWithGoogle()
                    .onEach { response ->
                        if (response is AuthResponse.Success) {
                            navigator.replace(MainScreen())
                        } else {
                            val error = response is AuthResponse.Error
                            Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
                        }

                    }.launchIn(scope)
            }
        )*/

        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
    }
}