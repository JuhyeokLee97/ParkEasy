package com.example.presentation.main.mypage

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.presentation.login.LoginActivity
import com.example.presentation.theme.ParkEasyTheme

@Composable
fun MyPageScreen(
    viewModel: MyPageViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffect.collect { effect ->
                when (effect) {
                    is MyPageSideEffect.Toast -> {
                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                    }

                    is MyPageSideEffect.Logout -> {
                        context.startActivity(
                            Intent(context, LoginActivity::class.java).apply {
                                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            }
                        )
                    }
                }
            }
        }
    }

    MyPageScreen(
        userEmail = uiState.email,
        onLogoutClick = viewModel::onLogoutClick
    )
}

@Composable
private fun MyPageScreen(
    modifier: Modifier = Modifier,
    userEmail: String,
    onLogoutClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ProfileImage(
            modifier = Modifier.size(150.dp),
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = userEmail,
            style = MaterialTheme.typography.headlineMedium
        )

        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = onLogoutClick,
        ) {
            Text("로그아웃")
        }
    }
}

@Composable
private fun ProfileImage(
    modifier: Modifier = Modifier,
    profileImageUrl: String? = null,
    borderWidth: Dp = 4.dp
) {
    Box {
        val rainbowColorsBrush = remember {
            Brush.sweepGradient(
                listOf(
                    Color(0xFF9575CD),
                    Color(0xFFBA68C8),
                    Color(0xFFE57373),
                    Color(0xFFFFB74D),
                    Color(0xFFFFF176),
                    Color(0xFFAED581),
                    Color(0xFF4DD0E1),
                    Color(0xFF9575CD)
                )
            )
        }
        Image(
            modifier = modifier
                .border(
                    BorderStroke(borderWidth, rainbowColorsBrush),
                    CircleShape
                )
                .padding(borderWidth)
                .clip(CircleShape),
            painter = rememberVectorPainter(image = Icons.Filled.Person),
            colorFilter = if (profileImageUrl == null) ColorFilter.tint(Color.White) else null,
            contentDescription = "프로필 이미지",
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    ParkEasyTheme {
        MyPageScreen(
            userEmail = "test@test.com",
            onLogoutClick = {},
        )
    }
}
