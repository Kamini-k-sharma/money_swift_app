package com.ecommerce.moneyswift.utils.designs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ecommerce.moneyswift.ui.theme.spacing


/**
 * @param image a nullable string of the image to be loaded
 * @param contentDescription desc of the image
 * @param modifier [Modifier]
 * @param topRadius top radius of the composable
 * @param bottomRadius radius bottom
 * @param height height in [Dp]
 * @param startPadding padding of the composable
 */
@Composable
fun FullWidthImageItem(
    image:Int,
    contentDescription: String,
    modifier: Modifier,
    topRadius: Dp = spacing().medium,
    bottomRadius: Dp = spacing().medium,
    height: Dp = 380.dp,
    startPadding: Dp = spacing().none,
) {
    Image(
        painter = painterResource(id =image),
        contentDescription = contentDescription,
        modifier
            .padding(start = startPadding, end = startPadding)
            .clip(
                RoundedCornerShape(
                    topStart = topRadius,
                    topEnd = topRadius,
                    bottomStart = bottomRadius,
                    bottomEnd = bottomRadius,
                ),
            )
            .fillMaxWidth()
            .height(height),
    )
}


