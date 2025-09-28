package com.example.parkeasy.ui.component

import androidx.compose.foundation.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.parkeasy.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseAppBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable () -> Unit = {}
) = TopAppBar(
    title = {
        Text(text = title)
    },
    navigationIcon = {
        onBackClick?.let {
            IconButton(onClick = onBackClick) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back"
                )
            }
        }
    },
    actions = { actions() }
)