package com.example.parkeasy.core.ui.components.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.window.DialogProperties

@Composable
fun ParkEasyDialog(
    title: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    content: (@Composable () -> Unit)? = null,
    confirmButton: @Composable () -> Unit,
    dismissButton: (@Composable () -> Unit)? = null,
    properties: DialogProperties = DialogProperties()
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        text = when {
            text != null -> {
                {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            content != null -> content
            else -> null
        },
        confirmButton = confirmButton,
        dismissButton = dismissButton,
        modifier = modifier,
        properties = properties
    )
}

@Composable
fun ParkEasyAlertDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    confirmText: String = "확인"
) {
    ParkEasyDialog(
        title = title,
        text = message,
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = confirmText,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun ParkEasyConfirmDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    confirmText: String = "확인",
    dismissText: String = "취소"
) {
    ParkEasyDialog(
        title = title,
        text = message,
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(
                    confirmText,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = dismissText)
            }
        },
        modifier = modifier
    )
}