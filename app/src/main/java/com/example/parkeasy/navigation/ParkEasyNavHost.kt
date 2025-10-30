package com.example.parkeasy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parkeasy.feature.around.AroundScreen
import com.example.parkeasy.feature.home.HomeScreen
import com.example.parkeasy.feature.mypage.MyPageScreen

@Composable
fun ParkEasyNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = ParkEasyDestination.Home.route
    ) {
        composable(route = ParkEasyDestination.Home.route) {
            HomeScreen(
                onNavigateToAroundParkingLot = {
                    navController.navigate(route = ParkEasyDestination.Around.route)
                },
                onNavigateToMyPage = {
                    navController.navigate(route = ParkEasyDestination.MyPage.route)
                }
            )
        }

        composable(route = ParkEasyDestination.Around.route) {
            AroundScreen(
                onNavigateToDetail = { parkingLotId -> },
                onNavigateToBack = { navController.popBackStack() }
            )
        }

        composable(route = ParkEasyDestination.MyPage.route) {
            MyPageScreen(
                onNavigateToBack = { navController.popBackStack() }
            )
        }
    }
}