package com.ugisozols.noteapp.presentation.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import com.ugisozols.noteapp.R
import com.ugisozols.noteapp.presentation.components.StandardTextField
import com.ugisozols.noteapp.presentation.ui.theme.*
import com.ugisozols.noteapp.utitilies.Constants.EMPTY_FIELD_ERROR
import com.ugisozols.noteapp.utitilies.Constants.PASSWORDS_DO_NOT_MATCH_ERROR
import com.ugisozols.noteapp.utitilies.Constants.SERVER_CONNECTION_ERROR
import com.ugisozols.noteapp.utitilies.Constants.TOO_SHORT_PASSWORD_ERROR
import com.ugisozols.noteapp.utitilies.Resource
import com.ugisozols.noteapp.utitilies.Screen

@Composable
fun RegistrationScreen(
    navController: NavController,
    registerViewModel: RegisterScreenViewModel = hiltViewModel()
) {
    Box(modifier = Modifier
        .background(color = BackgroundColor)
        .fillMaxSize()
        .padding(horizontal = paddingMedium)
    ){
        val mainNoteLogo = painterResource(id = R.drawable.ic_sticky_notes )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()

        ) {
            Image(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .size(150.dp),
                painter = mainNoteLogo,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(paddingLarge))
            RegistrationSection(navController = navController, viewModel = registerViewModel)
        }
    }

}

@Composable
fun RegistrationSection(
    navController : NavController,
    viewModel: RegisterScreenViewModel,

) {
    val email by viewModel.email.observeAsState(initial = "")
    val password by viewModel.password.observeAsState(initial = "")
    val confirmedPassword by viewModel.confirmedPassword.observeAsState(initial = "")
    val registerState by viewModel.register.observeAsState(Resource.Loading())
    var buttonIsClicked by remember {
        mutableStateOf(false)
    }


    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = stringResource(id = R.string.register_title),
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(paddingMedium))
        StandardTextField(
            hint = stringResource(id = R.string.register_email_hint),
            text = email,
            keyboardInputType = KeyboardType.Text,
            onValueChange = { newEmail ->
                viewModel.onEmailChange(newEmail)
            })
        Spacer(modifier = Modifier.height(paddingMedium))
        StandardTextField(
            hint = stringResource(id = R.string.register_password_hint),
            text = password,
            keyboardInputType = KeyboardType.Password,
            isVisible = false,
            onValueChange = { newPassword ->
                viewModel.onPasswordChange(newPassword)
            })
        Spacer(modifier = Modifier.height(paddingMedium))
        StandardTextField(
            hint = stringResource(id = R.string.register_confirmed_password_hint),
            keyboardInputType = KeyboardType.Password,
            text =confirmedPassword,
            isVisible = false,
            onValueChange = { newConfirmedPassword ->
                viewModel.onConfirmedPasswordChange(newConfirmedPassword)
            })
        Spacer(modifier = Modifier.height(paddingMedium))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End,verticalAlignment = Alignment.CenterVertically) {
            if (buttonIsClicked){
                RegisterState(registerState = registerState)
            }
            Spacer(modifier = Modifier.width(paddingMedium))
            Button(
                onClick = {
                    viewModel.registerUser(email, password, confirmedPassword)
                    buttonIsClicked = true
                },
                shape = RoundedCornerShape(textfieldRoundedCorners)
            ) {
                Text(text = stringResource(id = R.string.register_button_title),color = ButtonTextColor)
            }
      
        }

        Spacer(modifier = Modifier.height(spacerLarge))
        Text(
            modifier = Modifier
                .align(CenterHorizontally)
                .clickable {
                navController.navigate(Screen.Login.route)
            },
            text = buildAnnotatedString {
            append(stringResource(id = R.string.register_already_have_account))
            append(" ")
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.Bold
                )
            ){
                append(stringResource(id = R.string.register_login))
            }
        })
        Spacer(modifier = Modifier.height(spacerLarge))



    }

}

@Composable
fun RegisterState( registerState : Resource<String>) {
    val accountCreated = stringResource(id = R.string.register_account_created)
    val unknownError = stringResource(id = R.string.register_unknown_error)
    val usernameAlreadyExists = stringResource(id = R.string.register_username_exists)
    val registrationSuccessful = stringResource(id = R.string.register_successful)


    when(registerState){
        is Resource.Loading -> {
            CircularProgressIndicator(
                color = MainAccent,
                strokeWidth = loadingProgressBarWidth,
                modifier = Modifier
                    .size(registerProgressBarSize)
                    .wrapContentSize()
            )
        }
        is Resource.Success -> {
            when(registerState.data.toString()){
                accountCreated -> {
                    Text(
                        text = registrationSuccessful,
                        color = SuccessfulColor,
                        fontSize = errorFontSize
                    )
                }
                unknownError ->{
                    Text(
                        text = registerState.data.toString(),
                        color = ErrorColor,
                        fontSize = errorFontSize
                    )
                }
                usernameAlreadyExists ->{
                    Text(
                        text = registerState.data.toString(),
                        color = ErrorColor,
                        fontSize = errorFontSize
                    )
                }

            }
        }
        is Resource.Error -> {
            when(registerState.message.toString()){
                SERVER_CONNECTION_ERROR -> {
                    Text(
                        text = registerState.message?: "",
                        color = ErrorColor,
                        fontSize = errorFontSize
                    )
                }
                EMPTY_FIELD_ERROR -> {
                    Text(
                        text = registerState.message?: "",
                        color = ErrorColor,
                        fontSize = errorFontSize
                    )
                }
                TOO_SHORT_PASSWORD_ERROR ->{
                    Text(
                        text = registerState.message?: "",
                        color = ErrorColor,
                        fontSize = errorFontSize
                    )
                }
                PASSWORDS_DO_NOT_MATCH_ERROR ->{
                    Text(
                        text = registerState.message?: "",
                        color = ErrorColor,
                        fontSize = errorFontSize
                    )
                }
            }
        }

    }
}
