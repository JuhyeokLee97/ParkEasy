package com.example.parkeasy.core.ui.components.appbar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.parkeasy.core.ui.components.icon.AppIcon
import com.example.parkeasy.core.ui.model.icon.IconData

private val ICON_SIZE = 24.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParkEasyTopAppBar(
    title: String,
    navigationIconData: IconData = IconData.None,
    navigationIconContentDescription: String? = null,
    onNavigationClick: () -> Unit,
    actionIconData: IconData = IconData.None,
    actionIconContentDescription: String? = null,
    onActionClick: () -> Unit,
) = TopAppBar(
    title = {
        Text(text = title)
    },
    navigationIcon = {
        if (navigationIconData !is IconData.None) {
            AppBarIcon(
                iconData = navigationIconData,
                iconContentDescription = navigationIconContentDescription,
                onClick = onNavigationClick
            )
        }
    },
    actions = {
        if (actionIconData !is IconData.None) {
            AppBarIcon(
                iconData = actionIconData,
                iconContentDescription = actionIconContentDescription,
                onClick = onActionClick
            )
        }
    },
)

@Composable
private fun AppBarIcon(
    iconData: IconData,
    iconContentDescription: String? = null,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        AppIcon(
            iconData = iconData,
            contentDescription = iconContentDescription,
            modifier = Modifier.size(ICON_SIZE)
        )
    }
}