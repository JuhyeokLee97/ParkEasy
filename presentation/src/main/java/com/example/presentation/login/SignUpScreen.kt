package com.example.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.R
import com.example.presentation.theme.ParkEasyTheme

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateToLoginScreen: () -> Unit,
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val sideEffect by viewModel.sideEffect.collectAsStateWithLifecycle(
        initialValue = null
    )
    LaunchedEffect(sideEffect) {
        when (val effect = sideEffect) {
            is SignUpSideEffect.Toast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }

            is SignUpSideEffect.NavigateUp -> {
                navigateToLoginScreen()
            }

            else -> {}
        }
    }
    SignUpScreen(
        id = uiState.id,
        password = uiState.password,
        repeatPassword = uiState.repeatPassword,
        signUpEnabled = uiState.isSignUpEnabled,
        onIdChange = viewModel::onIdChange,
        onPasswordChange = viewModel::onPasswordChange,
        onRepeatPasswordChange = viewModel::onRepeatPasswordChange,
        onSignUpClick = viewModel::onSignUpClick
    )
}

@Composable
private fun SignUpScreen(
    modifier: Modifier = Modifier,
    id: String = "",
    password: String = "",
    repeatPassword: String = "",
    signUpEnabled: Boolean = false,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    keyboardController?.hide()
                }
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoSection()
            InputSection(
                id = id,
                password = password,
                repeatPassword = repeatPassword,
                signUpEnabled = signUpEnabled,
                onIdChange = onIdChange,
                onPasswordChange = onPasswordChange,
                onRepeatPasswordChange = onRepeatPasswordChange,
                onSignUpClick = onSignUpClick,
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
    signUpEnabled: Boolean = false,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val idFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val repeatPasswordFocusRequester = remember { FocusRequester() }

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
                    .focusRequester(idFocusRequester)
                    .fillMaxWidth(),
                value = id,
                onValueChange = onIdChange,
                label = { Text("이메일") },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        passwordFocusRequester.requestFocus()
                    }
                )
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "PASSWORD",
                style = MaterialTheme.typography.labelLarge
            )
            OutlinedTextField(
                modifier = Modifier
                    .focusRequester(passwordFocusRequester)
                    .fillMaxWidth(),
                value = password,
                onValueChange = onPasswordChange,
                label = { Text("비밀번호") },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        repeatPasswordFocusRequester.requestFocus()
                    }
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "REPEAT PASSWORD",
                style = MaterialTheme.typography.labelLarge
            )
            OutlinedTextField(
                modifier = Modifier
                    .focusRequester(repeatPasswordFocusRequester)
                    .fillMaxWidth(),
                value = repeatPassword,
                onValueChange = onRepeatPasswordChange,
                label = { Text("비밀번호 재확인") },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        onSignUpClick()
                    }
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                shape = RoundedCornerShape(4.dp),
                enabled = signUpEnabled,
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
        SignUpScreen(
            id = "",
            password = "",
            repeatPassword = "",
            onIdChange = {},
            onPasswordChange = {},
            onRepeatPasswordChange = {},
            onSignUpClick = {}
        )
    }
}