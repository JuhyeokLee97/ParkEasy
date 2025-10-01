package com.example.parkeasy.feature.mypage.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.parkeasy.R
import com.example.parkeasy.feature.mypage.presentation.input.MyPageInput
import com.example.parkeasy.feature.mypage.presentation.output.MyPageOutput
import com.example.parkeasy.ui.component.CommonAppBar
import com.example.parkeasy.ui.component.OneButton
import com.example.parkeasy.ui.component.ParkEasyBottomBar
import com.example.parkeasy.ui.component.ServicePreparingDialog
import com.example.parkeasy.ui.component.TopAppBar
import com.example.parkeasy.ui.theme.Paddings
import com.example.parkeasy.ui.theme.ParkEasyTheme

val MY_PAGE_SCREEN = "MY_PAGE_SCREEN"

@Composable
fun MyPageScreen(
    viewModel: MyPageViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {},
    onNavigateToInputCarInfo: () -> Unit = {},
    onNavigateToInputPaymentInfo: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {},
) {
    val output by viewModel.output.collectAsStateWithLifecycle(
        initialValue = MyPageOutput(
            uiState = MyPageOutput.UiState(),
            sideEffect = null
        )
    )

    LaunchedEffect(output.sideEffect) {
        when (output.sideEffect) {
            MyPageOutput.SideEffect.NavigateToCarInfo -> onNavigateToInputCarInfo()
            MyPageOutput.SideEffect.NavigateToPaymentInfo -> onNavigateToInputPaymentInfo()
            MyPageOutput.SideEffect.NavigateUp -> onBackClick()
            MyPageOutput.SideEffect.NavigateToLogin -> onNavigateToLogin()
            else -> {}
        }
    }

    ServicePreparingDialog(
        visible = output.uiState.showServicePreparingDialog,
        onDismiss = { viewModel.handleInput(MyPageInput.NavigateUp) }
    )

    Scaffold(
        topBar = {
            CommonAppBar.TopAppBar(
                title = stringResource(R.string.my_page_title),
                onBackClick = { viewModel.handleInput(MyPageInput.NavigateUp) }
            )
        },
        bottomBar = {
            ParkEasyBottomBar.OneButton(
                modifier = Modifier.padding(horizontal = Paddings.large),
                text = stringResource(R.string.logout),
                onClick = { viewModel.handleInput(MyPageInput.Logout) }
            )
        }
    ) { innerPadding ->
        BodyContent(
            modifier = Modifier.padding(innerPadding),
            onInputEvent = viewModel::handleInput,
        )
    }
}

@Composable
fun BodyContent(
    modifier: Modifier = Modifier,
    onInputEvent: (MyPageInput) -> Unit,
) {
    Column(
        modifier = modifier.padding(horizontal = Paddings.large),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Info
        ProfileInfoCard()
        // Services
        Spacer(modifier = Modifier.height(Paddings.large))
        ServiceCard(
            text = "차량 등록",
            onClick = { onInputEvent(MyPageInput.NavigateToCarInfo) }
        )

        Spacer(modifier = Modifier.height(Paddings.large))
        ServiceCard(
            text = "결제수단 등록",
            onClick = { onInputEvent(MyPageInput.NavigateToPaymentInfo) }
        )

        Spacer(modifier = Modifier.height(Paddings.large))
        ServiceCard(
            text = "예약 내역",
            onClick = { onInputEvent(MyPageInput.NavigateToReservationHistory) }
        )

        Spacer(modifier = Modifier.height(Paddings.large))
        ServiceCard(
            text = "설정",
            onClick = { onInputEvent(MyPageInput.NavigateToSetting) }
        )
    }
}

private val PROFILE_INFO_HEIGHT = 350.dp
private val PROFILE_IMAGE_SIZE = 200.dp

@Composable
fun ProfileInfoCard() {
    Card(elevation = CardDefaults.elevatedCardElevation(10.dp)) {
        Column(
            modifier = Modifier
                .height(PROFILE_INFO_HEIGHT)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.img_profile),
                contentDescription = "Profile Image",
                modifier = Modifier.size(PROFILE_IMAGE_SIZE)
            )

            Spacer(modifier = Modifier.height(Paddings.large))
            Text(
                text = "사용자 계정",
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}

private val SERVICE_BOX_SIZE = 20.dp

@Composable
fun ServiceCard(
    text: String,
    color: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = Paddings.large,
                    vertical = Paddings.medium
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(SERVICE_BOX_SIZE)
                    .background(color = color)
                    .padding(Paddings.large)
                    .clip(MaterialTheme.shapes.large)
            )
            Text(
                text = text,
                modifier = Modifier
                    .padding(Paddings.medium)
                    .weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_right),
                contentDescription = "ARROW_RIGHT"
            )
        }
    }
}

@Preview
@Composable
fun MyPageScreenPreview() {
    ParkEasyTheme {
        MyPageScreen { }
    }
}