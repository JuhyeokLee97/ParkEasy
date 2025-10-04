package com.example.parkeasy.feature.signup

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.parkeasy.R
import com.example.parkeasy.feature.signup.data.SignupOutput
import com.example.parkeasy.feature.signup.presentation.SignupViewModel
import com.example.parkeasy.ui.component.CommonAppBar
import com.example.parkeasy.ui.component.TopAppBar
import com.example.parkeasy.ui.theme.Paddings
import com.example.parkeasy.ui.theme.ParkEasyTheme

val SIGNUP_SCREEN = "SIGNUP_SCREEN"
private val ICON_SIZE = 128.dp

@Composable
fun SignupScreen(
    viewModel: SignupViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit
) {
    val sideEffect = viewModel.sideEffect
    LaunchedEffect(sideEffect) {
        sideEffect.collect {
            when (it) {
                is SignupOutput.SideEffect.NavigateUp -> onNavigateUp()
                else -> {}
            }
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CommonAppBar.TopAppBar(
                title = "회원가입",
                onBackClick = onNavigateUp
            )
        }
    ) { innerPadding ->
        Row(
            modifier = Modifier.padding(innerPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BodyContent(
                onSignup = { id, pw -> viewModel.requestSignup(id, pw) },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun BodyContent(
    onSignup: (String, String) -> Unit,
    modifier: Modifier
) {
    val id = remember { mutableStateOf("") }
    val pw = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(horizontal = Paddings.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LogoSection()
        Spacer(modifier = Modifier.height(Paddings.xExtra))
        InputSection(
            idValue = id.value,
            onIdValueChange = { id.value = it },
            pwValue = pw.value,
            onPwValueChange = { pw.value = it }
        )
        SignUpButton(id = id.value, pw = pw.value) {
            onSignup(id.value, pw.value)
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
fun ColumnScope.InputSection(
    idValue: String,
    onIdValueChange: (String) -> Unit,
    pwValue: String,
    onPwValueChange: (String) -> Unit
) {
    IdInput(
        value = idValue,
        onValueChange = onIdValueChange
    )
    Spacer(modifier = Modifier.height(Paddings.large))
    PwInput(
        value = pwValue,
        onValueChange = onPwValueChange
    )
}

@Composable
fun IdInput(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "test@test.com") }
    )
}

@Composable
fun PwInput(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "8자 이상 입력해주세요.") }
    )
}

@Composable
fun SignUpButton(id: String, pw: String, onSignup: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onSignup
    ) {
        Text(
            stringResource(id = R.string.signup),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
fun SignupScreenPreview() {
    ParkEasyTheme {
        SignupScreen { }
    }
}