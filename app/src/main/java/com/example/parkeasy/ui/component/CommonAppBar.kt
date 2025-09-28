package com.example.parkeasy.ui.component

import androidx.compose.runtime.Composable

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