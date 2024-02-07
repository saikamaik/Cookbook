package com.example.cookbook.presentation.view.recipe.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextName (
    recipeName: String
) {
    Text(
        text = "$recipeName",
        color = Color.DarkGray,
        fontSize = 25.sp,
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
        color = Color.DarkGray,
        fontSize = 12.sp
    )
}