package com.ugisozols.noteapp.presentation.notes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ugisozols.noteapp.R
import com.ugisozols.noteapp.data.local.entities.Notes
import com.ugisozols.noteapp.presentation.components.BottomNavBar
import com.ugisozols.noteapp.presentation.navigation.Navigation
import com.ugisozols.noteapp.presentation.ui.theme.MainAccent
import com.ugisozols.noteapp.presentation.ui.theme.loadingProgressBarWidth
import com.ugisozols.noteapp.utitilies.Constants
import com.ugisozols.noteapp.utitilies.Constants.LOGIN_SCREEN_ROUTE
import com.ugisozols.noteapp.utitilies.Constants.NOTES_SCREEN_ROUTE
import com.ugisozols.noteapp.utitilies.Constants.REGISTER_SCREEN_ROUTE
import com.ugisozols.noteapp.utitilies.NavBarItem
import com.ugisozols.noteapp.utitilies.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import timber.log.Timber

@Composable
fun NoteScreen(
    viewModel: NotesViewModel = hiltViewModel()
) {
    ShowNotes(viewModel = viewModel)
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        val navController = rememberNavController()
            BottomNavBar(
                items = listOf(
                    NavBarItem(
                        title = stringResource(id = R.string.nav_notes_title),
                        icon = Icons.Default.Favorite,
                        route = NOTES_SCREEN_ROUTE
                    ),
                    NavBarItem(
                        title = stringResource(id = R.string.nav_notes_title),
                        icon = Icons.Default.Favorite,
                        route = REGISTER_SCREEN_ROUTE
                    )
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }


}



    @Composable
    fun ShowNotes(
        viewModel: NotesViewModel
    ) {
        val notesDataState = viewModel.notes.observeAsState()
        val listIsEmpty = notesDataState.value?.data?.isEmpty() ?: return Unit
        when (notesDataState.value) {
            is Resource.Loading -> {
                CircularProgressIndicator(
                    color = MainAccent,
                    strokeWidth = loadingProgressBarWidth,
                    modifier = Modifier
                        .size(100.dp)
                        .wrapContentSize()
                )
            }
            is Resource.Success -> {
                if (listIsEmpty) {
                    EmptyState()
                } else {
                    Text(text = "There are ${notesDataState.value?.data?.lastIndex.toString()} notes")
                }
            }
            is Resource.Error -> {
                ErrorState()
            }
        }

    }


    @Composable
    fun EmptyState() {
        Text(text = "This is when empty list")
    }


    @Composable
    fun NotEmptyState() {
        Text(text = "This is when there are some list items")
    }


    @Composable
    fun ErrorState() {
        Text(text = "This is when Error state ")
    }
