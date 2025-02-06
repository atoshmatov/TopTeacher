package uz.toshmatov.bookpro.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.toshmatov.bookpro.core.extensions.getCurrentThemeMode
import uz.toshmatov.bookpro.core.theme.TopTeacherTheme
import uz.toshmatov.bookpro.data.local.datastore.OnboardingUtils
import uz.toshmatov.bookpro.presentation.screen.auth.signin.VoyagerLoginScreen
import uz.toshmatov.bookpro.presentation.screen.intro.VoyagerIntroScreen
import uz.toshmatov.bookpro.presentation.screen.main.VoyagerMainScreen

class TopTeacherActivity : ComponentActivity() {

    private val onboardingUtils by lazy { OnboardingUtils(this) }
    private val viewModel by viewModel<TopTeacherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        setContent {
            val themeMode by viewModel.isDarkMode.collectAsStateWithLifecycle()
            TopTeacherTheme(
                darkTheme = getCurrentThemeMode(themeMode),
            ) {
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