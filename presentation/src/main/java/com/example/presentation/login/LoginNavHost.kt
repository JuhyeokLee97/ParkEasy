package com.example.presentation.login

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginRoute.LoginScreen.name,
    ) {
        composable(route = LoginRoute.LoginScreen.name) {
            LoginScreen(
                id = "",
                password = "",
                onIdChange = {},
                onPasswordChange = {},
                onLoginClick = {},
                onSignUpClick = {
                    navController.navigate(route = LoginRoute.SignUpScreen.name)
                },
                onFindIdPasswordClick = {}
            )
        }
        composable(route = LoginRoute.SignUpScreen.name) {
            SignUpScreen(
                navigateToLoginScreen = {
                    navController.navigateUp()
                }
            )
        }
    }
}