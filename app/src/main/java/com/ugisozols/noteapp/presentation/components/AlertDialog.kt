package com.ugisozols.noteapp.presentation.components

import android.app.AlertDialog
import android.widget.Button
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.ugisozols.noteapp.presentation.ui.theme.ButtonTextColor
import com.ugisozols.noteapp.presentation.ui.theme.MainAccent
import com.ugisozols.noteapp.presentation.ui.theme.SurfaceColor
import com.ugisozols.noteapp.presentation.ui.theme.textfieldRaundedCorners

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
                modifier = Modifier.clip(RoundedCornerShape(textfieldRaundedCorners))
            ) {
                Text(text = confirm, color = ButtonTextColor)
            }
        },

        dismissButton = {
            Button(
                onClick = { onDeclineClick() },
                modifier = Modifier.clip(RoundedCornerShape(textfieldRaundedCorners))
            ) {
                Text(text = decline , color = ButtonTextColor)
            }
        }
    )
}