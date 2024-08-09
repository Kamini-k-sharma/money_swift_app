package com.ecommerce.moneyswift.utils.designs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ecommerce.moneyswift.R
import com.ecommerce.moneyswift.ui.theme.spacing

@Composable
fun NoResultFound(
    modifier: Modifier = Modifier,
    extraInfo: String = "Clear filters and explore more searches",
    title: String = "Nothing found!",
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("ecommerce_error_indicator"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        CommonText(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = spacing().small),
            style = MaterialTheme.typography.titleSmall,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = modifier.height(spacing().medium))

        CommonText(
            text = extraInfo,
            modifier = Modifier.padding(top = spacing().small),
            style = MaterialTheme.typography.bodyMedium,
        )

        Spacer(modifier = modifier.height(spacing().extraMedium))
    }
}
