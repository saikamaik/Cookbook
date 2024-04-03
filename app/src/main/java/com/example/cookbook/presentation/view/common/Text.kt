package com.example.cookbook.presentation.view.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.example.cookbook.ui.theme.TertiaryGray90
import com.example.cookbook.ui.theme.Typography

@Composable
fun TextName(
    recipeName: String
) {
    Text(
        text = recipeName,
        color = TertiaryGray90,
        style = Typography.labelSmall,
        textDecoration = TextDecoration.Underline
    )
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