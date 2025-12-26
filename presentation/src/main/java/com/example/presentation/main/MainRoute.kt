package com.example.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainRoute(
    val route: String,
    val icon: ImageVector,
    val contentDescription: String,
) {
    HOME(route = "HomeScreen", icon = Icons.Filled.Home, contentDescription = "홈"),
    AROUND_LIST(route = "AroundListScreen", icon = Icons.AutoMirrored.Filled.List, contentDescription = "주변 주차장 목록"),
    MY_PAGE(route = "SettingScreen", icon = Icons.Filled.AccountCircle, contentDescription = "내 정보"),
}