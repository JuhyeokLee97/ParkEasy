package com.example.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.theme.ParkEasyTheme

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoSection()
            InputSection(
                onIdChange = {},
                onPasswordChange = {},
                onRepeatPasswordChange = {},
                onSignUpClick = {},
            )
        }
    }
}

@Composable
private fun LogoSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_app_logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(128.dp)
        )
        Text(
            text = "ParkEasy",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "회원가입",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun InputSection(
    modifier: Modifier = Modifier,
    id: String = "",
    password: String = "",
    repeatPassword: String = "",
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .padding(top = 24.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            modifier = modifier.padding(horizontal = 36.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 36.dp),
                text = "ID",
                style = MaterialTheme.typography.labelLarge
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = id,
                onValueChange = onIdChange,
                label = { Text("아이디") }
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "PASSWORD",
                style = MaterialTheme.typography.labelLarge
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = onPasswordChange,
                label = { Text("비밀번호") }
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "REPEAT PASSWORD",
                style = MaterialTheme.typography.labelLarge
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = repeatPassword,
                onValueChange = onRepeatPasswordChange,
                label = { Text("비밀번호 재확인") }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                shape = RoundedCornerShape(4.dp),
                onClick = onSignUpClick
            ) {
                Text(text = "회원가입")
            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
    ParkEasyTheme {
        SignUpScreen()
    }
}