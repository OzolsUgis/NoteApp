package com.ugisozols.noteapp.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ugisozols.noteapp.R
import com.ugisozols.noteapp.presentation.components.StandardTextField
import com.ugisozols.noteapp.presentation.ui.theme.*
import com.ugisozols.noteapp.utitilies.Constants.EMPTY_FIELD_ERROR
import com.ugisozols.noteapp.utitilies.Constants.NOTES_SCREEN_ROUTE
import com.ugisozols.noteapp.utitilies.Constants.NO_EMAIL
import com.ugisozols.noteapp.utitilies.Constants.NO_PASSWORD
import com.ugisozols.noteapp.utitilies.Resource
import com.ugisozols.noteapp.utitilies.Screen



@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()

) {

    val currentlyLoggedInEmail = viewModel.loggedInEmail
    val currentlyLoggedInPassword = viewModel.loggedInPassword


    if (isLoggedIn(currentlyLoggedInEmail?:"",currentlyLoggedInPassword ?: "")){
        viewModel.login(currentlyLoggedInEmail ?: "",currentlyLoggedInPassword ?: "")
        viewModel.authenticate(currentlyLoggedInEmail ?: "", currentlyLoggedInPassword ?: "")
        LaunchedEffect(Unit){
            navController.navigate(Screen.Notes.route){
                popUpTo(Screen.Login.route){
                    inclusive = true
                }
            }
        }

    }else{
        val logo = painterResource(id = R.drawable.ic_sticky_notes)
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = paddingMedium)
            ) {
                LogoSection(logo = logo, modifier = Modifier.size(logoSize))
                Spacer(modifier = Modifier.height(paddingLarge))
                LoginInputSection(viewModel, navController)
                Spacer(modifier = Modifier.height(paddingLarge))
                Spacer(modifier = Modifier.height(paddingLarge))
                CreateAccount(navController = navController)
                Spacer(modifier = Modifier.height(200.dp))
            }

        }
    }





}


private fun isLoggedIn(curEmail : String, curPassword: String): Boolean {
    return curEmail != NO_EMAIL && curPassword != NO_PASSWORD
}

@Composable
fun LoginInputSection(viewModel: LoginViewModel,navController: NavController) {
    val email by viewModel.email.observeAsState("")
    val password by viewModel.password.observeAsState("")
    val loginState by viewModel.login.observeAsState(Resource.Loading())
    var buttonIsClicked by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.login_title),
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(paddingMedium))
        StandardTextField(
            text = email,
            onValueChange = { newEmail ->
                viewModel.onEmailChange(newEmail)
            },
            hint = stringResource(id = R.string.register_email_hint)
        )
        Spacer(modifier = Modifier.height(paddingMedium))
        StandardTextField(
            text = password,
            onValueChange = { newPassword ->
                viewModel.onPasswordChange(newPassword)
            },
            hint = stringResource(id = R.string.register_password_hint),
            keyboardInputType = KeyboardType.Password,
            isVisible = false
        )
        Spacer(Modifier.height(paddingMedium))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (buttonIsClicked){

                LoginState(loginState = loginState, navController)
            }


            Spacer(modifier = Modifier.width(paddingMedium))
            Button(
                onClick = {
                    viewModel.login(email, password)
                    viewModel.authenticate(email,password)
                    viewModel.setSharedPreferencesEmailAndPassword(email, password)
                    buttonIsClicked = true

                },
                shape = RoundedCornerShape(textfieldRoundedCorners)
            ) {

                Text(text = stringResource(id = R.string.login_button_title),color = ButtonTextColor)
            }

        }
    }
}



@Composable
fun LoginState(loginState : Resource<String>, navController: NavController){
        val incorrectCredentials = stringResource(id = R.string.login_incorrect_credentials)
        val loginPassed = stringResource(id = R.string.login_passed)
        when (loginState) {
            is Resource.Loading -> {
                CircularProgressIndicator(
                    color = MainAccent,
                    strokeWidth = loadingProgressBarWidth,
                    modifier = Modifier
                        .size(30.dp)
                        .wrapContentSize()
                )
            }
            is Resource.Success -> {
                when (loginState.data.toString()) {
                    incorrectCredentials -> {
                        Text(
                            text = loginState.data.toString(),
                            color = ErrorColor,
                            fontSize = errorFontSize
                        )
                    }
                    loginPassed -> {
                        LaunchedEffect(Unit ){
                            navController.navigate(NOTES_SCREEN_ROUTE){
                                popUpTo(Screen.Login.route){
                                    inclusive = true
                                }
                            }

                        }
                    }
                }
            }
            is Resource.Error -> {
                when (loginState.message) {
                    EMPTY_FIELD_ERROR -> {
                        Text(
                            text = loginState.message,
                            color = ErrorColor,
                            fontSize = errorFontSize
                        )
                    }
                }

            }

        }
}

@Composable
fun CreateAccount(navController: NavController) {
    Text(
        modifier = Modifier.clickable {
            navController.navigate(Screen.Register.route)
        },
        text = stringResource(id = R.string.create_account),
        style = MaterialTheme.typography.body1,
    )
}

@Composable
fun LogoSection(logo : Painter, modifier: Modifier) {
    Image(
        modifier = modifier,
        painter = logo,
        contentDescription = null,
    )
}