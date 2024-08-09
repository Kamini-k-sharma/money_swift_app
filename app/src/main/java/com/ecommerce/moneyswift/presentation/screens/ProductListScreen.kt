package com.ecommerce.moneyswift.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecommerce.moneyswift.data.model.Product
import com.ecommerce.moneyswift.presentation.state.UiState
import com.ecommerce.moneyswift.ui.theme.spacing
import com.ecommerce.moneyswift.utils.designs.CommonText
import com.ecommerce.moneyswift.utils.designs.NoResultFound


@Composable
fun ProductListScreen(state: UiState, onProductSelected: (Product) -> Unit) {

    if (state.productList.isNotEmpty()) {
        Column {
            TopItem()
            LazyColumn {
                items(state.productList) { product ->
                    ProductListItem(product, onProductSelected)
                }
            }
        }
    } else if (state.errorMsg?.isNotEmpty() == true) {
        // show error
        ErrorItem(state = state)
    } else if (state.productList.isEmpty()) {
        // empty view
        NoResultFound()
    }


}

@Composable
fun TopItem(modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CommonText(
            text = "Money Swift",
            textPadding = 8.dp,
            fontSize = 20.sp,
            textColor = Color.White
        )

    }
}

@Composable
fun ProductListItem(product: Product, onProductSelected: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onProductSelected(product) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = product.name,
                modifier = Modifier
                    .height(80.dp)
            )
            Column {
                Text(text = product.name, fontSize = 20.sp)
                Text(text = "$${product.price}", fontSize = 16.sp, color = Color.Gray)
            }
            Button(onClick = { onProductSelected(product) }) {
                Text("Buy")
            }
        }
    }

}

@Composable
fun ErrorItem(state: UiState) {
    CommonText(
        modifier = Modifier.testTag("ecommerce_error_text"),
        text = "Error Fetching listings .... ${state.errorMsg} ",
        textPadding = spacing().medium
    )
}



