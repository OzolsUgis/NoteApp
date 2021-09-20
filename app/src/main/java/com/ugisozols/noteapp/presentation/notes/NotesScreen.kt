package com.ugisozols.noteapp.presentation.notes


import android.widget.Space
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ugisozols.noteapp.R
import com.ugisozols.noteapp.data.local.entities.Notes
import com.ugisozols.noteapp.presentation.components.BottomNavBar
import com.ugisozols.noteapp.presentation.ui.theme.*
import com.ugisozols.noteapp.utitilies.Constants.NOTES_SCREEN_ROUTE
import com.ugisozols.noteapp.utitilies.Constants.REGISTER_SCREEN_ROUTE
import com.ugisozols.noteapp.utitilies.NavBarItem
import com.ugisozols.noteapp.utitilies.Resource
import com.ugisozols.noteapp.utitilies.Screen
import timber.log.Timber

@Composable
fun NoteScreen(
    navController : NavController,
    viewModel: NotesViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        Column() {
            TopBar(navController = navController)
            Headline()
            Spacer(modifier = Modifier.height(paddingLarge))
            Box(
                modifier =Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopStart
            ){
                Scaffold(
                    floatingActionButtonPosition = FabPosition.End,
                    floatingActionButton = {
                            FloatingActionButton(backgroundColor = MainAccent,contentColor = SurfaceColor,onClick = {
                                navController.navigate(Screen.NewNotes.route)
                            }) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        }

                    }){
                    ShowNotes(navController,viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun Headline() {
    Text(
        modifier = Modifier . padding(start = paddingMedium),
        text = stringResource(id = R.string.all_notes),
        style = MaterialTheme.typography.h1
    )
}
@Composable
fun TopBar(navController: NavController) {

    TopAppBar(
        backgroundColor = BackgroundColor,
        contentColor = MainAccent,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    end = paddingMedium
                )
        ) {
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "LogOut",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        navController.navigate(Screen.Login.route)
                    }
            )
        }

    }
}





    @Composable
    fun ShowNotes(
        navController: NavController,
        viewModel: NotesViewModel
    ) {
        val notesDataState = viewModel.notes.observeAsState()
        val listIsEmpty = notesDataState.value?.data?.isEmpty() ?: return
        when (notesDataState.value) {
            is Resource.Loading -> {
                CircularProgressIndicator(
                    color = MainAccent,
                    strokeWidth = loadingProgressBarWidth,
                    modifier = Modifier
                        .size(progressIndicator)
                        .wrapContentSize()
                )
            }
            is Resource.Success -> {
                if (listIsEmpty) {
                    EmptyState()
                } else {
                    val list = notesDataState.value?.data?.toList()
                    SuccessfullyLoadedNotes(note = list,navController)
                }
            }
            is Resource.Error -> {
                ErrorState()
            }
        }

    }


    @Composable
    private fun EmptyState() {
        Box(contentAlignment = Alignment.Center,modifier = Modifier
            .fillMaxSize()
            .padding(
                paddingLarge
            )){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(modifier = Modifier.size(logoSize),painter = painterResource(id = R.drawable.ic_sticky_notes) , contentDescription = null)
                Spacer(modifier = Modifier.height(paddingLarge))
                Text(textAlign = TextAlign.Center,text = stringResource(id = R.string.welcome_to_notes))
                Spacer(Modifier.height(emptyNotesWelcomeBottomSpacer))
            }
        }
    }


    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun SuccessfullyLoadedNotes(note : List<Notes>?, navController: NavController) {
        val notes = note.orEmpty()
        Timber.d(notes.size.toString())
        LazyVerticalGrid(cells = GridCells.Adaptive(gridCellsWidth)){
            items(notes){
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(paddingSmall)) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(noteCardHeight)
                            .padding()
                            .clickable {
                                navController.navigate(Screen.NoteDetail.withArgs(it.id))
                            }
                        ,
                        shape = RoundedCornerShape(cardRoundedCorners),
                        backgroundColor = SurfaceColor
                    ) {
                        Column() {
                            Text(modifier = Modifier.padding(
                                top = paddingMedium,
                                start = paddingMedium
                            ),text = it.title, style = MaterialTheme.typography.body1)
                            Text(modifier = Modifier.padding(
                                top = paddingSmall,
                                start = paddingMedium,
                                bottom = paddingLarge,
                                end = paddingSmall
                            ), text = it.content, fontWeight = FontWeight.Normal)
                        }

                    }
                    Spacer(modifier = Modifier.width(paddingSmall))
                }
                }
            }
        }



    @Composable
    private fun ErrorState() {
        Box(contentAlignment = Alignment.Center,modifier = Modifier
            .fillMaxSize()
            .padding(
                paddingLarge
            )){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(modifier = Modifier.size(logoSize),painter = painterResource(id = R.drawable.ic_sticky_notes) , contentDescription = null)
                Spacer(modifier = Modifier.height(paddingLarge))
                Text(textAlign = TextAlign.Center,text = stringResource(id = R.string.register_unknown_error),color = Color.Red)
                Spacer(Modifier.height(emptyNotesWelcomeBottomSpacer))
            }
        }
    }
