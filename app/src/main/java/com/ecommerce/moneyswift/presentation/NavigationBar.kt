package com.ecommerce.moneyswift.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun NavigationBar(onBack: () -> Unit) {
    IconButton(
        onClick = onBack,
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
}