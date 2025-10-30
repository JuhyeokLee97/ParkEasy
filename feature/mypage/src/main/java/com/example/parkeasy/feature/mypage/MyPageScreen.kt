package com.example.parkeasy.feature.mypage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.parkeasy.feature.mypage.presentation.MyPageViewModel
import com.example.parkeasy.feature.mypage.presentation.component.MyPageScreenContent

@Composable
fun MyPageScreen(
    viewModel: MyPageViewModel = hiltViewModel(),
    onNavigateToBack: () -> Unit,
){

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadProfileInfo()
    }

    MyPageScreenContent(
        uiState = uiState,
        onNavigateToBack = onNavigateToBack
    )
}