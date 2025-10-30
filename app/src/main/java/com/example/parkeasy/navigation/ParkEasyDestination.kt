package com.example.parkeasy.navigation

sealed class ParkEasyDestination(val route: String) {
    object Home : ParkEasyDestination("home")
    object Around : ParkEasyDestination("around")
    object MyPage : ParkEasyDestination("myPage")
}