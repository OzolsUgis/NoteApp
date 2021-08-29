package com.ugisozols.noteapp.presentation.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.LiveData
import com.ugisozols.noteapp.R
import com.ugisozols.noteapp.presentation.components.StandardTextField
import com.ugisozols.noteapp.presentation.ui.theme.*
import com.ugisozols.noteapp.utitilies.Resource
import timber.log.Timber

@Composable
fun RegistrationScreen(
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
                contentDescription = "Notes Logo",
            )
            Spacer(modifier = Modifier.height(paddingLarge))
            RegistrationSection(modifier = Modifier.fillMaxWidth(), viewModel = registerViewModel)
        }
    }

}

@Composable
fun RegistrationSection(
    modifier: Modifier = Modifier,
    viewModel: RegisterScreenViewModel,

) {
    val email by viewModel.email.observeAsState(initial = "")
    val password by viewModel.password.observeAsState(initial = "")
    val confirmedPassword by viewModel.confirmedPassword.observeAsState(initial = "")
    val registerState by viewModel.register.observeAsState(initial = Resource.Loading())

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
        Button(
            onClick = {
                viewModel.registerUser(email, password, confirmedPassword)
                Timber.d("this is from on click")
                when(registerState){
                    is Resource.Loading ->{
                        Timber.d("this is from on click Loading state")
                    }
                    is Resource.Success ->{
                        Timber.d("this is from on click success state")
                    }
                    is Resource.Error ->{
                        Timber.d("this is from on click error state")
                        Timber.d(registerState.message)
                            Timber.d(password)
                            Timber.d(confirmedPassword)
                    }

                }
            },
            modifier = Modifier
                .align(Alignment.End),
            shape = RoundedCornerShape(textfieldRaundedCorners)
        ) {
            Text(text = stringResource(id = R.string.register_button_title),color = ButtonTextColor)
        }
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            modifier = Modifier.align(CenterHorizontally),
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
        Spacer(modifier = Modifier.height(100.dp))



    }

}