package uz.toshmatov.bookpro.presentation.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import uz.toshmatov.bookpro.core.theme.TopTeacherColors
import uz.toshmatov.bookpro.core.theme.TopTeacherDimensions
import uz.toshmatov.bookpro.core.uicomponent.CurrencyIcon
import uz.toshmatov.bookpro.core.utils.drawable

class VoyagerMainScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        MainScreen()
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(TopTeacherColors.background),
    ) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))

        IconButton(
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 12.dp),
            onClick = {},
        ) {
            Icon(
                painter = painterResource(drawable.ic_tab_profile),
                contentDescription = "",
                tint = TopTeacherColors.button
            )
        }

        LazyColumn(
            modifier = Modifier
                .background(TopTeacherColors.background),
        ) {
            items(10) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(100.dp)
                        .padding(
                            start = 12.dp,
                            end = 12.dp,
                            top = 6.dp,
                            bottom = 6.dp
                        )
                        .clip(RoundedCornerShape(12.dp))
                        .background(TopTeacherColors.itemBackground),
                ) {
                    Text("Item")
                }
            }
        }

        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
    }
}