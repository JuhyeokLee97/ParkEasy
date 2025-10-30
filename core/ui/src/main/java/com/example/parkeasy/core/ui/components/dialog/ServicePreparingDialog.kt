package com.example.parkeasy.core.ui.components.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ServicePreparingDialog(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ParkEasyDialog(
        title = "서비스 준비중",
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "확인",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Build,
                    contentDescription = "Preparing Service"
                )

                Column {
                    Text(text = "해당 기능은 현재 준비중입니다.")
                    Text(
                        text = "빠른 시일 내에 제공하겠습니다.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        },
        modifier = modifier
    )
}