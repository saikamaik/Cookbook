package com.example.cookbook.presentation.view.recipe.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cookbook.model.RecipeModel

@Composable
fun RecipeCard(
    recipe: RecipeModel
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val extraPadding = if (expanded) 4.dp else 0.dp
    val onClick = { expanded = !expanded }

    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier
                    .clickable(onClick = onClick)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(bottom = extraPadding)
                ) {
                    TextName(
                        recipeName = recipe.name.orEmpty()
                    )
                    if (expanded) TextDesc(recipeShortDesc = recipe.shortDesc.orEmpty())
                }
            }
        }
        Spacer(
            modifier = Modifier.weight(1f)
        )
    }
}