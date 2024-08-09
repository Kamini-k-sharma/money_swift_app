package com.ecommerce.moneyswift.utils.designs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.ecommerce.moneyswift.ui.theme.spacing

@Composable
fun CommonText(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.Black,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = Int.MAX_VALUE,
    fontSize: TextUnit = 15.8.sp,
    textDecoration: TextDecoration = TextDecoration.None,
    textAlign: TextAlign = TextAlign.Start,
    textPadding: Dp = spacing().none,
    backColor: Color = Color.Transparent,
    shapeRadius: Dp = spacing().none,
) {
    Text(
        text = text,
        style = style,
        color = textColor,
        maxLines = maxLines,
        overflow = overflow, fontSize = fontSize,
        textAlign = textAlign,
        textDecoration = textDecoration,
        modifier =
        modifier
            .background(
                color = backColor,
                shape = RoundedCornerShape(shapeRadius),
            )
            .padding(textPadding),
    )
}
