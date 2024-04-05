package com.example.cookbook.presentation.view.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.cookbook.ui.theme.TertiaryGray90
import com.example.cookbook.ui.theme.Typography

@Composable
fun TextMedium(
    modifier: Modifier = Modifier,
    text: String,
    color: Color?,
    fontWeight: FontWeight? = null
) {
    if (color != null) {
        Text(
            text = text,
            style = Typography.bodyMedium,
            color = color,
            fontWeight = fontWeight,
            modifier = modifier
        )
    } else {
        Text(
            text = text,
            style = Typography.bodyMedium,
            color = TertiaryGray90,
            fontWeight = fontWeight,
            modifier = modifier
        )
    }
}

@Composable
fun TextHeader4(
    header: String,
    modifier: Modifier
) {
    Text(
        text = header,
        style = Typography.bodyLarge,
        fontWeight = FontWeight.SemiBold,
        color = TertiaryGray90,
        modifier = modifier
    )
}

@Composable
fun TextHeader(
    header: String,
    modifier: Modifier
) {
    Text(
        text = header,
        style = Typography.labelMedium,
        fontWeight = FontWeight.SemiBold,
        color = TertiaryGray90,
        modifier = modifier
    )
}