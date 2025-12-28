package com.example.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.R
import com.example.presentation.component.ParkEasyOutlinedTextField
import com.example.presentation.theme.ParkEasyTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    onSignUpClick: () -> Unit,
    onFindIdPasswordClick: () -> Unit,
    onNavigateToMainActivity: () -> Unit,
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sideEffect by viewModel.sideEffect.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(Unit) {
        when (val effect = sideEffect) {
            is LoginSideEffect.Toast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }

            is LoginSideEffect.NavigateToHome -> {
                onNavigateToMainActivity()
            }

            else -> {
                Toast.makeText(context, "ELSE", Toast.LENGTH_SHORT).show()
            }
        }
    }

    LoginScreen(
        id = uiState.id,
        password = uiState.password,
        isLoginEnabled = uiState.isLoginEnabled,
        onIdChange = viewModel::onIdChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = viewModel::onLoginClick,
        onSignUpClick = onSignUpClick,
        onFindIdPasswordClick = onFindIdPasswordClick,
    )
}

@Composable
private fun LoginScreen(
    id: String = "",
    password: String = "",
    isLoginEnabled: Boolean = false,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onFindIdPasswordClick: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures {
                        keyboardController?.hide()
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LogoSection()
            Spacer(modifier = Modifier.height(48.dp))
            InputSection(
                modifier = Modifier.fillMaxWidth(fraction = 0.8f),
                id = id,
                password = password,
                isLoginEnabled = isLoginEnabled,
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
    isLoginEnabled: Boolean = false,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val idFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember {
        FocusRequester()
    }
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ParkEasyOutlinedTextField(
            modifier = Modifier
                .focusRequester(idFocusRequester)
                .fillMaxWidth(),
            value = id,
            onValueChange = onIdChange,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    passwordFocusRequester.requestFocus()
                }
            ),
            label = { Text("이메일") },
        )

        ParkEasyOutlinedTextField(
            modifier = Modifier
                .focusRequester(passwordFocusRequester)
                .fillMaxWidth(),
            value = password,
            onValueChange = onPasswordChange,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    onLoginClick()
                }
            ),
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("비밀번호") },
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            enabled = isLoginEnabled,
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