package com.example.parkeasy.feature.mypage.mypage.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.parkeasy.core.ui.components.appbar.ParkEasyTopAppBar
import com.example.parkeasy.core.ui.model.icon.IconData
import com.example.parkeasy.feature.mypage.R
import com.example.parkeasy.feature.mypage.mypage.model.MyPageUiState

@Composable
fun MyPageScreenContent(
    uiState: MyPageUiState,
    onNavigateToBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            ParkEasyTopAppBar(
                title = "마이페이지",
                navigationIconData = IconData.Resource(painter = painterResource(R.drawable.ic_back)),
                navigationIconContentDescription = "뒤로 가기",
                onNavigationClick = onNavigateToBack,
            )
        }
    ) { innerPadding ->
        when (uiState) {
            is MyPageUiState.Loading -> {
                MyPageLoadingState(modifier = Modifier.padding(innerPadding))
            }

            is MyPageUiState.Success -> {
                MyPageSuccessState(
                    modifier = Modifier.padding(innerPadding),
                    uiState = uiState
                )
            }

            is MyPageUiState.Error -> {
                MyPageErrorState()
            }
        }
    }
}