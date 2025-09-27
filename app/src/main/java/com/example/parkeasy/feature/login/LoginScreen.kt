package com.example.parkeasy.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parkeasy.R
import com.example.parkeasy.ui.theme.Paddings
import com.example.parkeasy.ui.theme.ParkEasyTheme
import com.example.parkeasy.ui.theme.underlinedText

private val ICON_SIZE = 128.dp

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(ICON_SIZE)
                .background(Color.DarkGray)
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
        Spacer(
            modifier = Modifier.height(Paddings.xExtra)
        )
        InputContent()
    }
}

@Composable
fun InputContent() {
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
            value = "",
            onValueChange = {},
            label = { Text("아이디") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {},
            label = { Text("비밀번호") }
        )

        Spacer(
            modifier = Modifier.height(Paddings.large)
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        ) {
            Text(
                stringResource(id = R.string.login),
                style = MaterialTheme.typography.labelLarge
            )
        }
        Spacer(
            modifier = Modifier.height(Paddings.large)
        )
        Text(
            modifier = Modifier.clickable {

            },
            text = stringResource(id = R.string.find_id_password),
            style = MaterialTheme.typography.underlinedText,
        )
        Spacer(modifier = Modifier.width(Paddings.xExtra))
        Text(
            modifier = Modifier.clickable {

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
        LoginScreen(modifier = Modifier.fillMaxSize())
    }
}