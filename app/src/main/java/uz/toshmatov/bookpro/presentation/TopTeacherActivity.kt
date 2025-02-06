package uz.toshmatov.bookpro.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import uz.toshmatov.bookpro.core.theme.TopTeacherTheme
import uz.toshmatov.bookpro.data.local.datastore.OnboardingUtils
import uz.toshmatov.bookpro.presentation.screen.auth.signin.VoyagerLoginScreen
import uz.toshmatov.bookpro.presentation.screen.intro.VoyagerIntroScreen
import uz.toshmatov.bookpro.presentation.screen.main.VoyagerMainScreen

class TopTeacherActivity : ComponentActivity() {

    private val onboardingUtils by lazy { OnboardingUtils(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            TopTeacherTheme {
                Navigator(
                    screen = if (onboardingUtils.isOnboardingCompleted()) {
                        if (onboardingUtils.isLogin()) {
                            VoyagerMainScreen()
                        } else {
                            VoyagerLoginScreen()
                        }
                    } else {
                        VoyagerIntroScreen()
                    }
                ) {
                    SlideTransition(it)
                }
            }
        }
    }
}