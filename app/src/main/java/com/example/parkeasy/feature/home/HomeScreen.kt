package com.example.parkeasy.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.parkeasy.R
import com.example.parkeasy.ui.component.CommonAppBar
import com.example.parkeasy.ui.component.TopAppBarWithAction
import com.example.parkeasy.ui.theme.Paddings
import com.example.parkeasy.ui.theme.ParkEasyTheme

val HOME_SCREEN = "HOME_SCREEN"

@Composable
fun HomeScreen(
    onNavigateToParkEasy: () -> Unit = {},
    onNavigateToMyPage: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CommonAppBar.TopAppBarWithAction(
                title = stringResource(id = R.string.app_name),
                onActionClick = onNavigateToMyPage,
                actionResId = R.drawable.img_profile,
                contentDescription = "Profile Image"
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = MaterialTheme.colorScheme.secondary),
            )

            Row(
                modifier = Modifier
                    .padding(
                        vertical = Paddings.large,
                        horizontal = Paddings.medium
                    )
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = onNavigateToParkEasy,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "주변 주차장 찾기",
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.width(Paddings.large))
                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f),
                ) {
                    Text(
                        text = "즐겨찾기",
                        style = MaterialTheme.typography.labelLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    ParkEasyTheme {
        HomeScreen()
    }
}