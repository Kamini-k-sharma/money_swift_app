package com.ecommerce.moneyswift.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ecommerce.moneyswift.viewModels.MoneySwiftViewModel
import com.flexcode.ecommerce.presentation.event.HomeEvent

@Composable
fun ConfirmationScreen(
    viewModel: MoneySwiftViewModel = hiltViewModel(),
    toBack: () -> Unit,
) {
    val onEvent = viewModel::onEvent
    LaunchedEffect(key1 = true) {
        onEvent(HomeEvent.ResetState)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(
            onClick = toBack,
            colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Black),
            modifier = Modifier
                .testTag("success_back_btn")
                .size(60.dp, 60.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Payment Successful!", fontSize = 24.sp, color = Color.Green)

        }
    }
}

