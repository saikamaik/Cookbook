package com.example.cookbook.presentation.view.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookbook.ui.theme.TertiaryGray90
import com.example.cookbook.ui.theme.Typography

@Composable
fun TextName (
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
fun TextDesc(
    recipeShortDesc: String
) {
    Text(
        text = recipeShortDesc,
        Modifier.padding(top = 6.dp),
        color = TertiaryGray90,
        fontSize = 12.sp
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