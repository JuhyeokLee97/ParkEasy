package com.example.parkeasy.feature.mypage.presentation.component

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.parkeasy.core.ui.util.Paddings
import com.example.parkeasy.feature.mypage.R
import com.example.parkeasy.feature.mypage.model.MyPageUiState

@Composable
fun MyPageSuccessState(
    modifier: Modifier = Modifier,
    uiState: MyPageUiState.Success,
) {
    Column(
        modifier = modifier.padding(horizontal = Paddings.large),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ProfileInfoCard(userEmail = uiState.userEmail)
        Spacer(modifier = Modifier.height(Paddings.large))

        ServiceCard(
            text = "차량 등록",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(Paddings.large))

        ServiceCard(
            text = "결제수단 등록",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(Paddings.large))

        ServiceCard(
            text = "예약 내역",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(Paddings.large))

        ServiceCard(
            text = "설정",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(Paddings.large))
    }
}

@Composable
private fun ProfileInfoCard(
    modifier: Modifier = Modifier,
    userEmail: String?
) {
    val profileInfoHeight = 350.dp
    val profileImageSize = 200.dp

    Card(
        elevation = CardDefaults.elevatedCardElevation(10.dp)
    ) {
        Column(
            modifier = modifier
                .height(profileInfoHeight)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(R.drawable.img_profile),
                contentDescription = "Profile Image",
                modifier = Modifier.size(profileImageSize)
            )
            Spacer(modifier = Modifier.height(Paddings.large))
            Text(
                text = userEmail ?: "사용자 계정",
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun ServiceCard(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
) {
    val serviceBoxSize = 20.dp

    Card(
        modifier = modifier.fillMaxWidth(),
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
                    .size(serviceBoxSize)
                    .background(color = backgroundColor)
                    .padding(Paddings.large)
                    .clip(MaterialTheme.shapes.large)
            )
            Text(
                text =text,
                modifier = Modifier
                    .padding(Paddings.medium)
                    .weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_right),
                contentDescription = "arrow right"
            )
        }
    }
}