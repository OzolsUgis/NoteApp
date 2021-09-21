package com.ugisozols.noteapp.presentation.components


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.ugisozols.noteapp.presentation.ui.theme.ButtonTextColor
import com.ugisozols.noteapp.presentation.ui.theme.textfieldRoundedCorners

@Composable
fun ShowAlertDialog(
    title: String,
    content: String,
    confirm: String,
    decline: String,
    onDismiss : () -> Unit,
    onConfirmClick: () -> Unit,
    onDeclineClick: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismiss()},
        title = { Text(text = title)},
        text = { Text(text = content, style = MaterialTheme.typography.body2)},
        confirmButton = {
            Button(
                onClick = { onConfirmClick() },
                modifier = Modifier.clip(RoundedCornerShape(textfieldRoundedCorners))
            ) {
                Text(text = confirm, color = ButtonTextColor)
            }
        },

        dismissButton = {
            Button(
                onClick = { onDeclineClick() },
                modifier = Modifier.clip(RoundedCornerShape(textfieldRoundedCorners))
            ) {
                Text(text = decline , color = ButtonTextColor)
            }
        }
    )
}