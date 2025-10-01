package com.example.parkeasy

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.parkeasy.feature.around.presentation.AROUND_PARK_SCREEN
import com.example.parkeasy.feature.around.presentation.AroundParkScreen
import com.example.parkeasy.feature.detail.presentation.DETAIL_SCREEN
import com.example.parkeasy.feature.detail.presentation.DetailScreen
import com.example.parkeasy.feature.detail.presentation.DetailViewModel
import com.example.parkeasy.feature.home.HOME_SCREEN
import com.example.parkeasy.feature.home.HomeScreen
import com.example.parkeasy.feature.inputcarinfo.presentation.INPUT_CAR_INFO_SCREEN
import com.example.parkeasy.feature.inputcarinfo.presentation.InputCarInfoScreen
import com.example.parkeasy.feature.inputpayment.presentation.INPUT_PAYMENT_INFO_SCREEN
import com.example.parkeasy.feature.inputpayment.presentation.InputPaymentInfoScreen
import com.example.parkeasy.feature.login.LOGIN_SCREEN
import com.example.parkeasy.feature.login.LoginScreen
import com.example.parkeasy.feature.mypage.presentation.MY_PAGE_SCREEN
import com.example.parkeasy.feature.mypage.presentation.MyPageScreen
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
                    onNavigateToHome = {
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
                        navController.navigate(route = AROUND_PARK_SCREEN)
                    },
                    onNavigateToMyPage = {
                        navController.navigate(route = MY_PAGE_SCREEN)
                    }
                )
            }
        }

        composable(route = AROUND_PARK_SCREEN) {
            ParkEasyTheme {
                AroundParkScreen(
                    onNavigateToDetail = { parkingLotId ->
                        navController.navigate(route = "$DETAIL_SCREEN/$parkingLotId")
                    },
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }

        composable(
            route = "$DETAIL_SCREEN/{${DetailViewModel.PARKING_LOT_ID}}",
            arguments = listOf(
                navArgument(name = DetailViewModel.PARKING_LOT_ID) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            ParkEasyTheme {
                DetailScreen(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }

        composable(route = MY_PAGE_SCREEN) {
            ParkEasyTheme {
                MyPageScreen(
                    onBackClick = {
                        navController.navigateUp()
                    },
                    onNavigateToInputCarInfo = {
                        navController.navigate(route = INPUT_CAR_INFO_SCREEN)
                    },
                    onNavigateToInputPaymentInfo = {
                        navController.navigate(route = INPUT_PAYMENT_INFO_SCREEN)
                    },
                    onNavigateToLogin = {
                        navController.navigate(route = LOGIN_SCREEN) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
        }

        composable(route = INPUT_CAR_INFO_SCREEN) {
            ParkEasyTheme {
                InputCarInfoScreen(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }

        composable(route = INPUT_PAYMENT_INFO_SCREEN) {
            ParkEasyTheme {
                InputPaymentInfoScreen(
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}