package com.example.parkeasy.core.ui.components.preview.dialog

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.parkeasy.core.ui.components.dialog.ParkEasyAlertDialog
import com.example.parkeasy.core.ui.components.dialog.ParkEasyConfirmDialog
import com.example.parkeasy.core.ui.components.dialog.ServicePreparingDialog

@Preview(showBackground = true)
@Composable
private fun PreviewParkEasyAlertDialog() {
    MaterialTheme {
        ParkEasyAlertDialog(
            title = "서비스 준비중",
            message = "해당 기능은 현재 준비중입니다.\n조금만 기다려주세요!",
            onDismiss = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewParkEasyConfirmDialog() {
    MaterialTheme {
        ParkEasyConfirmDialog(
            title = "Title",
            message = "Message",
            onConfirm = {},
            onDismiss = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewParkEasyServicePreparingDialog() {
    MaterialTheme {
        ServicePreparingDialog(
            onDismiss = {}
        )
    }
}