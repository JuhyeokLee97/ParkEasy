package com.example.parkeasy.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parkeasy.ui.theme.Paddings
import com.example.parkeasy.ui.theme.ParkEasyTheme

object ParkEasyBottomBar

val BUTTON_ROUND = 12.dp
val BUTTON_VERTICAL_PADDING = 24.dp
val BUTTON_HORIZONTAL_PADDING = 16.dp

@Composable
fun ParkEasyBottomBar.OneButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Button(
        modifier = modifier
            .padding(vertical = BUTTON_VERTICAL_PADDING)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(color)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ParkEasyBottomBar.RowTowButtons(
    modifier: Modifier = Modifier,
    firstButtonText: String,
    firstButtonOnClick: () -> Unit = {},
    secondButtonText: String,
    secondButtonOnClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .padding(
                horizontal = Paddings.large,
                vertical = Paddings.medium
            )
            .windowInsetsPadding(WindowInsets.navigationBars)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Paddings.medium)
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                MaterialTheme.colorScheme.surfaceContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            onClick = firstButtonOnClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = firstButtonText,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
            )
        }

        Button(
            onClick = secondButtonOnClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = secondButtonText,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun OneButtonPreview() {
    ParkEasyTheme {
        ParkEasyBottomBar.OneButton(
            text = "주차하기",
            onClick = {}
        )
    }
}