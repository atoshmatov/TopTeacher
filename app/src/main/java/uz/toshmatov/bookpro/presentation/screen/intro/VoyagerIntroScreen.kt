package uz.toshmatov.bookpro.presentation.screen.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.toshmatov.bookpro.R
import uz.toshmatov.bookpro.core.theme.TopTeacherColors
import uz.toshmatov.bookpro.core.uicomponent.CompletedButton
import uz.toshmatov.bookpro.data.local.datastore.OnboardingUtils
import uz.toshmatov.bookpro.presentation.screen.auth.signin.VoyagerLoginScreen
import uz.toshmatov.bookpro.presentation.screen.intro.components.IntroPage
import uz.toshmatov.bookpro.presentation.screen.intro.components.PagerIndicator

class VoyagerIntroScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current

        val onboardingUtils by lazy { OnboardingUtils(context) }

        IntroScreenContent(
            openScreen = {
                onboardingUtils.setOnboardingCompleted()
                navigator.replaceAll(VoyagerLoginScreen())
            }
        )
    }
}

@Composable
fun IntroScreenContent(
    modifier: Modifier = Modifier,
    openScreen: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { 2 })

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(TopTeacherColors.background)
    ) {
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> IntroPage(image = R.drawable.ic_intro_one)
                else -> IntroPage(image = R.drawable.ic_intro_two)
            }
        }
        CompletedButton(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            onCompleted = openScreen,
            textContent = R.string.get_started
        )
        PagerIndicator(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp), pagerState = pagerState
        )
    }
}

