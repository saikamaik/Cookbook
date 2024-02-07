package com.example.cookbook.presentation.view.recipe.components

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AddRecipeActionButton(
    openDialog: () -> Unit
) {
    FloatingActionButton(
        onClick = openDialog,
        Modifier.background(Color.Gray)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Добавить рецепт"
        )
    }
}