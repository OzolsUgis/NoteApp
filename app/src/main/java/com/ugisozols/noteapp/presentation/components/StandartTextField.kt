package com.ugisozols.noteapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ugisozols.noteapp.presentation.ui.theme.SurfaceColor
import com.ugisozols.noteapp.presentation.ui.theme.textfieldRaundedCorners


@Composable
fun StandardTextField(
    hint : String = "",
    text : String = "",
    maxLength : Int = 50,
    error : String = "",
    maxLines : Int = 1,
    singleLine : Boolean = true,
    isVisible : Boolean = true,
    keyboardInputType : KeyboardType = KeyboardType.Text,
    onValueChange : (String)-> Unit
) {
    TextField(
        shape = RoundedCornerShape(textfieldRaundedCorners),
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = SurfaceColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        value = text,
        onValueChange = { input->
            if(input.length < maxLength){
                onValueChange(input)
            }
        },
        maxLines = maxLines,
        singleLine = singleLine,
        visualTransformation =
            if(isVisible){
                VisualTransformation.None
            }else{
                PasswordVisualTransformation()
                },
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.body2
            )
        },
        isError = error!= "",
        keyboardOptions = KeyboardOptions(keyboardType = keyboardInputType)

    )
}