package com.example.parkeasy.core.ui.components.icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil3.compose.AsyncImage
import com.example.parkeasy.core.ui.model.icon.IconData

@Composable
fun AppIcon(
    iconData: IconData,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified,
) {
    when (iconData) {
        is IconData.Vector -> {
            Icon(
                imageVector = iconData.imageVector,
                contentDescription = contentDescription,
                modifier = modifier,
                tint = tint,
            )
        }

        is IconData.Resource -> {
            Icon(
                painter = iconData.painter,
                contentDescription = contentDescription,
                modifier = modifier,
                tint = tint,
            )
        }

        is IconData.Url -> {
            AsyncImage(
                model = iconData.url,
                contentDescription = contentDescription,
                modifier = modifier,
            )
        }

        is IconData.None -> {}
    }
}