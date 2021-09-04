package com.ugisozols.noteapp.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ugisozols.noteapp.R
import com.ugisozols.noteapp.presentation.components.StandardTextField
import com.ugisozols.noteapp.presentation.registration.RegisterState
import com.ugisozols.noteapp.presentation.ui.theme.*
import com.ugisozols.noteapp.utitilies.Constants
import com.ugisozols.noteapp.utitilies.Constants.EMPTY_FIELD_ERROR
import com.ugisozols.noteapp.utitilies.Resource
import com.ugisozols.noteapp.utitilies.Screen
import timber.log.Timber

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val logo = painterResource(id = R.drawable.ic_sticky_notes)
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = paddingMedium)
        ) {
            LogoSection(logo = logo, modifier = Modifier.size(220.dp))
            Spacer(modifier = Modifier.height(paddingLarge))
            LoginInputSection(viewModel)
            Spacer(modifier = Modifier.height(paddingLarge))
            Spacer(modifier = Modifier.height(paddingLarge))
            CreateAccount(navController = navController)
            Spacer(modifier = Modifier.height(200.dp))
        }

    }
}

@Composable
fun LoginInputSection(viewModel: LoginViewModel) {
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
                LoginState(loginState = loginState )
            }
            Spacer(modifier = Modifier.width(paddingMedium))
            Button(
                onClick = {
                    viewModel.login(email, password)
                    buttonIsClicked = true
                    Timber.d("this is from on click")
                },
                shape = RoundedCornerShape(textfieldRaundedCorners)
            ) {
                Text(text = stringResource(id = R.string.login_button_title),color = ButtonTextColor)
            }

        }
    }
}

@Composable
fun LoginState(loginState : Resource<String>) {
    val incorrectCredentials = stringResource(id = R.string.login_incorrect_credentials)
    val loginPassed = stringResource(id = R.string.login_passed)
    when(loginState){
        is Resource.Loading->{
            Timber.d("This is loading state ")
            CircularProgressIndicator(
                color = MainAccent,
                strokeWidth = loadingProgressBarWidth,
                modifier = Modifier
                    .size(30.dp)
                    .wrapContentSize()
            )
        }
         is Resource.Success->{
             Timber.d("This is success state ")
                when(loginState.data.toString()){
                    incorrectCredentials ->{
                        Text(
                            text = loginState.data.toString(),
                            color = ErrorColor,
                            fontSize = errorFontSize
                        )
                    }
                    loginPassed -> {
                        // Passes navigation route to home route
                        Timber.d("Login Successful")
                    }
                }
         }
        is Resource.Error->{
            Timber.d("This is error state ")
            when(loginState.message){
                EMPTY_FIELD_ERROR ->{
                    Text(
                        text = loginState.message?: "This is null case",
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
        contentDescription = "Notes Logo",
    )
}