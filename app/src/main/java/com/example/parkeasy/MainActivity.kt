package com.example.parkeasy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.parkeasy.navigation.ParkEasyNavHost
import com.example.parkeasy.ui.theme.ParkEasyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParkEasyTheme {
                ParkEasyNavHost()
//                MainNavigation()
            }
        }
    }
}

