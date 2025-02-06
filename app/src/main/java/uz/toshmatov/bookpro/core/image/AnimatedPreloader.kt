package uz.toshmatov.bookpro.core.image

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun AnimatedPreloader(modifier: Modifier = Modifier, lottie: Int) {
    var isPlaying by remember { mutableStateOf(true) }

    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            lottie
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        modifier = modifier,
        progress = {
            if (preloaderProgress == 1f) {
                isPlaying = true
            }
            preloaderProgress
        }
    )
}