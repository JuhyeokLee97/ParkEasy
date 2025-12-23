package com.example.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
}

@Composable
private fun HomeScreen() {
    Surface {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "ParkEasy")

            }
        }
    }
}