package com.example.parkeasy.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.parkeasy.ui.theme.ParkEasyTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi

val HOME_SCREEN = "HOME_SCREEN"

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    onNavigateToParkEasy: () -> Unit = {},
    onNavigateToMyPage: () -> Unit = {}
) {
}

@Preview
@Composable
fun HomeScreenPreview() {
    ParkEasyTheme {
        HomeScreen()
    }
}