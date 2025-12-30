package com.example.presentation.login

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginNavHost(
    navigateToMainActivity: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginRoute.LoginScreen.name,
    ) {
        composable(route = LoginRoute.LoginScreen.name) {
            LoginScreen(
                onSignUpClick = {
                    navController.navigate(route = LoginRoute.SignUpScreen.name)
                },
                onFindIdPasswordClick = {},
                onNavigateToMainActivity = navigateToMainActivity
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