package com.example.presentation.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.main.aroundlist.AroundListScreen
import com.example.presentation.main.home.HomeScreen
import com.example.presentation.main.mypage.MyPageScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MainRoute.HOME.route
    ) {
        composable(route = MainRoute.HOME.route) {
            HomeScreen()
        }
        composable(route = MainRoute.AROUND_LIST.route) {
            AroundListScreen()
        }
        composable(route = MainRoute.MY_PAGE.route) {
            MyPageScreen()
        }
    }
}