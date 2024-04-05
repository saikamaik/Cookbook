package com.example.cookbook.presentation.view.bookmarkScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cookbook.domain.Recipes

@Composable
fun BookmarkContent(
    recipes: Recipes,
    navController: NavHostController,
    onClick: (String) -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = recipes) { recipe ->
            RecipeBookmarkCard(
                recipe = recipe,
                navController
            ) {
                onClick(it)
            }
        }
    }

}