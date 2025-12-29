package com.example.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.theme.ParkEasyTheme
import com.example.presentation.util.SyncSystemBars
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            ParkEasyTheme {
                SyncSystemBars(
                    syncStatusBar = true,
                    syncNavigationBar = true
                )
                MainScreen()
            }
        }
    }
}