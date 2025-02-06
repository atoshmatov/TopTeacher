package uz.toshmatov.bookpro.presentation.screen.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import org.koin.androidx.compose.koinViewModel
import uz.toshmatov.bookpro.core.logger.logInfo
import uz.toshmatov.bookpro.core.theme.TopTeacherColors
import uz.toshmatov.bookpro.core.theme.TopTeacherDimensions
import uz.toshmatov.bookpro.core.uicomponent.LoadingScreen
import uz.toshmatov.bookpro.core.uicomponent.SearTextField
import uz.toshmatov.bookpro.core.uicomponent.makeToast
import uz.toshmatov.bookpro.core.utils.drawable
import uz.toshmatov.bookpro.data.remote.models.Teacher
import uz.toshmatov.bookpro.presentation.screen.main.component.RatingBar
import uz.toshmatov.bookpro.presentation.screen.main.component.RatingBarUpdate
import uz.toshmatov.bookpro.presentation.screen.main.component.RatingDialog
import uz.toshmatov.bookpro.presentation.screen.main.component.TeacherItem
import uz.toshmatov.bookpro.presentation.screen.setting.VoyagerSettingScreen

class VoyagerMainScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        MainScreen()
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val currentNavigator = LocalNavigator.currentOrThrow
    val viewModel = koinViewModel<MainViewModel>()
    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current

    val isShowRatingBar = rememberSaveable { mutableStateOf(false) }
    val teacherRating = rememberSaveable { mutableFloatStateOf(0f) }
    val teacherId = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(TopTeacherColors.background),
    ) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))

        Row {
            IconButton(
                modifier = Modifier
                    .padding(end = 12.dp),
                onClick = { context.makeToast("Tez kunda") },
            ) {
                Icon(
                    painter = painterResource(drawable.ic_tab_profile),
                    contentDescription = "",
                    tint = TopTeacherColors.button
                )
            }
            Spacer(Modifier.weight(1f))
            IconButton(
                modifier = Modifier
                    .padding(end = 12.dp),
                onClick = { currentNavigator.push(VoyagerSettingScreen()) },
            ) {
                Icon(
                    painter = painterResource(drawable.ic_setting),
                    contentDescription = "",
                    tint = TopTeacherColors.button
                )
            }
        }

        if (state.isLoading) {
            LoadingScreen()
        } else {
            TeacherList(
                teachers = state.teachers.toPersistentList(),
                searchQuery = { query ->
                    viewModel.reduce(MainEvent.SearchQuery(query))
                },
                itemClick = { teacher ->
                    logInfo { "teacher --> ${teacher.teacher_id} ${teacher.rating}" }
                    teacherRating.floatValue = teacher.rating ?: 0f
                    teacherId.value = teacher.teacher_id ?: ""
                    isShowRatingBar.value = true
                }
            )
        }
        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
    }

    if (isShowRatingBar.value) {
        RatingDialog(
            currentRating = teacherRating.floatValue,
            onDismiss = { isShowRatingBar.value = false },
            onRatingConfirmed = { newRating ->
                teacherRating.floatValue = newRating
                viewModel.reduce(MainEvent.UpdateTeacherRating(teacherId.value, newRating))
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TeacherList(
    teachers: ImmutableList<Teacher>,
    searchQuery: (String) -> Unit,
    itemClick: (teacher: Teacher) -> Unit
) {
    LazyColumn {
        stickyHeader {
            SearTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TopTeacherColors.background)
                    .padding(
                        horizontal = TopTeacherDimensions.medium,
                        vertical = TopTeacherDimensions.small
                    ),
                onValueChange = searchQuery,
            )
        }
        items(
            items = teachers,
            key = {
                it.teacher_id ?: ""
            }
        ) { teacher ->
            TeacherItem(
                teacher = teacher,
                itemClick = {
                    itemClick(teacher)
                }
            )
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

