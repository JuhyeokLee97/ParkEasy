package com.example.parkeasy.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

object CommonAppBar

@Composable
fun CommonAppBar.TopAppBar(
    title: String,
    onBackClick: () -> Unit
) {
    BaseAppBar(
        title = title,
        onBackClick = onBackClick
    )
}

@Composable
fun CommonAppBar.TopAppBarWithAction(
    title: String,
    onActionClick: () -> Unit,
    @DrawableRes actionResId: Int,
    contentDescription: String? = null
) {
    BaseAppBar(
        title = title,
        onBackClick = null,
        actions = {
            IconButton(
                onClick = onActionClick
            ) {
                Image(
                    painter = painterResource(id = actionResId),
                    contentDescription = contentDescription
                )
            }
        }
    )
}