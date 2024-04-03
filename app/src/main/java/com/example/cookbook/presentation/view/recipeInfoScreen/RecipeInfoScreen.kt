package com.example.cookbook.presentation.view.recipeInfoScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun RecipeInfoScreen(

) {

    val viewModel: RecipeInfoViewModel = hiltViewModel()
    val configuration = LocalConfiguration.current

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(20.dp)
    ) {
        AsyncImage(
            model = viewModel.recipe?.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .clip(RoundedCornerShape(15.dp))
        )
    }
}