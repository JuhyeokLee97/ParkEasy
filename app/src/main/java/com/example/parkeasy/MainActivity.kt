package com.example.parkeasy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parkeasy.feature.home.HOME_SCREEN
import com.example.parkeasy.feature.home.HomeScreen
import com.example.parkeasy.feature.login.LOGIN_SCREEN
import com.example.parkeasy.feature.login.LoginScreen
import com.example.parkeasy.ui.theme.ParkEasyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParkEasyTheme {
                MainNavigation()
            }
        }
    }
}

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
                HomeScreen()
            }
        }
    }
}