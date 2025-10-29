package com.example.parkeasy.core.ui.components.option

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.parkeasy.core.ui.util.Paddings

private val OPTION_BORDER_WIDTH = 2.dp
private val ROUND_CORNER_RADIUS = 50.dp

@Composable
fun CardTypeOption(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val selectedContentColor = MaterialTheme.colorScheme.primary
    val unselectedContentColor = Color.Gray
    val selectedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f)
    val unselectedContainerColor = Color.Transparent

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(ROUND_CORNER_RADIUS))
            .border(
                width = OPTION_BORDER_WIDTH,
                color = if (selected) selectedContentColor else unselectedContentColor,
                shape = RoundedCornerShape(ROUND_CORNER_RADIUS)
            )
            .background(
                color = if (selected) selectedContainerColor else unselectedContainerColor
            )
            .padding(
                start = Paddings.none,
                end = Paddings.extra,
                top = Paddings.small,
                bottom = Paddings.small
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Paddings.none),
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = selectedContentColor,
                unselectedColor = unselectedContentColor
            ),
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = if (selected) selectedContentColor else unselectedContentColor,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}