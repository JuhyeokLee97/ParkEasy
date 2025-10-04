package com.example.parkeasy.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.parkeasy.R
import com.example.parkeasy.ui.component.ServicePreparingDialog
import com.example.parkeasy.ui.theme.Paddings
import com.example.parkeasy.ui.theme.ParkEasyTheme
import com.example.parkeasy.ui.theme.underlinedText

val LOGIN_SCREEN = "LOGIN_SCREEN"
private val ICON_SIZE = 128.dp

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit = {},
    onNavigateToSignUp: () -> Unit = {},
) {
    val uiOutput by viewModel.uiOutput.collectAsStateWithLifecycle(
        initialValue = LoginOutput()
    )
    val sideEffect by viewModel.sideEffect.collectAsStateWithLifecycle(
        initialValue = null
    )
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(sideEffect) {
        when (sideEffect) {
            is LoginOutput.SideEffect.NavigateToHome -> onNavigateToHome()
            is LoginOutput.SideEffect.NavigateToSignUp -> onNavigateToSignUp()
            null -> {}
        }
    }

    LaunchedEffect(uiOutput.uiState.errorMessage) {
        uiOutput.uiState.errorMessage?.let {
            snackBarHostState.showSnackbar(it)
        }
    }

    ServicePreparingDialog(
        visible = uiOutput.uiState.showServicePreparingDialog,
        onDismiss = { viewModel.handleInput(LoginInput.DismissDialog) }
    )

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LogoSection()

                Spacer(modifier = Modifier.height(Paddings.xExtra))

                InputContent(
                    formState = uiOutput.formState,
                    uiState = uiOutput.uiState,
                    onInputEvent = viewModel::handleInput
                )
            }
        }
    }
}

@Composable
private fun ColumnScope.LogoSection() {
    Image(
        painter = painterResource(R.drawable.ic_app_logo),
        contentDescription = "App Logo",
        modifier = Modifier.size(ICON_SIZE)
    )
    Text(
        text = stringResource(id = R.string.app_name),
        style = MaterialTheme.typography.displayLarge,
    )

    Text(
        text = stringResource(id = R.string.app_desc),
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center
    )
}

@Composable
fun InputContent(
    formState: LoginOutput.FormState,
    uiState: LoginOutput.UiState,
    onInputEvent: (LoginInput) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                top = Paddings.large,
                start = Paddings.xExtra,
                end = Paddings.xExtra
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = formState.id,
            onValueChange = {
                onInputEvent(LoginInput.UpdateId(it))
            },
            label = { Text(text = stringResource(R.string.id_label)) }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = formState.password,
            onValueChange = {
                onInputEvent(LoginInput.UpdatePassword(it))
            },
            label = { Text(text = stringResource(R.string.pw_label)) },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(Paddings.large))
        // 로그인 버튼
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onInputEvent(LoginInput.LoginClicked) }
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(
                    stringResource(id = R.string.login),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
        Spacer(
            modifier = Modifier.height(Paddings.large)
        )
        // 하단 링크들
        Text(
            modifier = Modifier.clickable {
                onInputEvent(LoginInput.FindIdPasswordClicked)
            },
            text = stringResource(id = R.string.find_id_password),
            style = MaterialTheme.typography.underlinedText,
        )
        Spacer(modifier = Modifier.width(Paddings.xExtra))
        Text(
            modifier = Modifier.clickable {
                onInputEvent(LoginInput.SignupClicked)
            },
            text = stringResource(id = R.string.signup),
            style = MaterialTheme.typography.underlinedText,
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    ParkEasyTheme {
        LoginScreen()
    }
}