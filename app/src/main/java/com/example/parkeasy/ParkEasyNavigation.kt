package com.example.parkeasy

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parkeasy.feature.arround.AroundParkScreen
import com.example.parkeasy.feature.arround.PARK_EASY_SCREEN
import com.example.parkeasy.feature.detail.presentation.DETAIL_SCREEN
import com.example.parkeasy.feature.detail.presentation.DetailScreen
import com.example.parkeasy.feature.home.HOME_SCREEN
import com.example.parkeasy.feature.home.HomeScreen
import com.example.parkeasy.feature.login.LOGIN_SCREEN
import com.example.parkeasy.feature.login.LoginScreen
import com.example.parkeasy.ui.theme.ParkEasyTheme

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LOGIN_SCREEN
    ) {
        composable(route = LOGIN_SCREEN) {
            ParkEasyTheme {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(route = HOME_SCREEN) {
                            popUpTo(LOGIN_SCREEN) { inclusive = true }
                        }
                    }
                )
            }
        }

        composable(route = HOME_SCREEN) {
            ParkEasyTheme {
                HomeScreen(
                    onNavigateToParkEasy = {
                        navController.navigate(route = PARK_EASY_SCREEN)
                    }
                )
            }
        }

        composable(route = PARK_EASY_SCREEN) {
            ParkEasyTheme {
                AroundParkScreen(
                    onNavigateToDetail = {
                        navController.navigate(route = DETAIL_SCREEN)
                    }
                )
            }
        }

        composable(route = DETAIL_SCREEN) {
            ParkEasyTheme {
                DetailScreen(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}