package com.example.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.theme.ParkEasyTheme

@Composable
fun LoginScreen(
    id: String = "",
    password: String = "",
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onFindIdPasswordClick: () -> Unit,
) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LogoSection()
            Spacer(modifier = Modifier.height(48.dp))
            InputSection(
                modifier = Modifier.fillMaxWidth(fraction = 0.8f),
                id = id,
                password = password,
                onIdChange = onIdChange,
                onPasswordChange = onPasswordChange,
                onLoginClick = onLoginClick
            )
            OptionSection(
                onSignUpClick = onSignUpClick,
                onFindIdPasswordClick = onFindIdPasswordClick
            )
        }
    }
}

@Composable
private fun LogoSection(modifier: Modifier = Modifier) {
    Column(
        modifier,
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
            text = "쉽고 빠르게 주차장 찾기",
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
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = id,
            onValueChange = onIdChange,
            label = { Text("아이디") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("비밀번호") }
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            onClick = onLoginClick
        ) {
            Text(text = "로그인")
        }
    }
}

@Composable
private fun OptionSection(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit,
    onFindIdPasswordClick: () -> Unit,
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable { onFindIdPasswordClick() },
            text = "아이디/비밀번호 찾기",
            style = MaterialTheme.typography.labelSmall.copy(
                textDecoration = TextDecoration.Underline
            ),
        )
        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable { onSignUpClick() },
            text = "회원가입",
            style = MaterialTheme.typography.labelSmall.copy(
                textDecoration = TextDecoration.Underline
            ),
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    ParkEasyTheme {
        LoginScreen(
            id = "",
            password = "",
            onIdChange = {},
            onPasswordChange = {},
            onLoginClick = {},
            onSignUpClick = {},
            onFindIdPasswordClick = {}
        )
    }
}