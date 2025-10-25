package com.example.parkeasy.core.ui.components.preview.option

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.parkeasy.core.ui.components.option.CardTypeOption

@Preview
@Composable
fun PreviewCardTypeOption() {
    CardTypeOption(
        text = "카드 타입",
        selected = true,
        onClick = {}
    )
}