package com.ecommerce.moneyswift.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ecommerce.moneyswift.presentation.NavigationBar
import com.ecommerce.moneyswift.ui.theme.spacing
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
        /*  IconButton(
              onClick = toBack,
              colors = IconButtonDefaults.iconButtonColors(containerColor = secondaryColor),
              modifier = Modifier.testTag("success_back_btn")
          ) {
              Icon(
                  imageVector = Icons.Default.ArrowBack,
                  contentDescription = null,
                  tint = Color.White,
              )
          }*/
        NavigationBar {
            toBack()
        }
        Column(
            modifier = Modifier
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                modifier = Modifier.size(60.dp, 60.dp),
                tint = Color.Green,
            )
            Spacer(modifier = Modifier.width(spacing().small))
            Text(text = "Payment Successful!", fontSize = 24.sp, color = Color.Green)

        }
    }
    BackHandler(enabled = true, onBack = toBack)  //Handling system back press
}



