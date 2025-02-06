package uz.toshmatov.bookpro.presentation.screen.intro.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import uz.toshmatov.bookpro.core.theme.TopTeacherColors

@Composable
fun PagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color by animateColorAsState(
                targetValue = if (pagerState.currentPage == iteration) TopTeacherColors.button
                else TopTeacherColors.iconGray,
                animationSpec = tween(durationMillis = 300), label = ""
            )
            val width by animateDpAsState(
                targetValue = if (pagerState.currentPage == iteration) 18.dp else 9.dp,
                animationSpec = tween(durationMillis = 300), label = ""
            )
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .width(width)
                    .height(4.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color)
            )
        }
    }
}