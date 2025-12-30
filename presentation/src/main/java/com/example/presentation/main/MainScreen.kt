package com.example.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presentation.R
import com.example.presentation.theme.ParkEasyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route?.let { route ->
        MainRoute.entries.find { it.route == route }
    } ?: MainRoute.HOME
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        },
        content = { paddingValues ->
            MainNavHost(
                modifier = Modifier.padding(paddingValues),
                navController = navController
            )
        },
        bottomBar = {
            MainBottomBar(
                currentRoute = currentRoute,
                onItemClick = { newRoute ->
                    navController.navigate(route = newRoute.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        this.launchSingleTop = true
                        this.restoreState = true
                    }
                }
            )
        }
    )
}

@Composable
private fun MainBottomBar(
    modifier: Modifier = Modifier,
    currentRoute: MainRoute = MainRoute.HOME,
    onItemClick: (MainRoute) -> Unit,
) {
    Column(modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)) {
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            MainRoute.entries.forEach { route ->
                IconButton(onClick = { onItemClick(route) }) {
                    Icon(
                        imageVector = route.icon,
                        contentDescription = route.contentDescription,
                        tint = if (currentRoute == route) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            Color.White
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainBottomBarPreview() {
    ParkEasyTheme {
        MainBottomBar(
            onItemClick = { }
        )
    }
}