package com.example.cookbook.presentation.view.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cookbook.R
import com.example.cookbook.presentation.view.homeScreen.sections.PopularCategory
import com.example.cookbook.ui.theme.TertiaryGray90
import com.example.cookbook.ui.theme.Typography

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier.padding(start = 24.dp, top = 20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.find_best_recipe_with_line_break),
            style = Typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = TertiaryGray90,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 10.dp)
        )
        PopularCategory(
            viewModel = viewModel
        )
    }

//    Scaffold(
//        bottomBar = {
//            NavigationBar(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                contentColor = MaterialTheme.colorScheme.primary,
//            ) {
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    textAlign = TextAlign.Center,
//                    text = "Bottom app bar",
//                )
//            }
//        },
//        content = { paddingValues ->
//            Recipes(
//                recipeContent = { recipes ->
//                    RecipeContent(
//                        padding = paddingValues,
//                        recipes = recipes
//                    )
//                }
//            )
//            if (openDialog) {
//                AddRecipeDialog(
//                    closeDialog = {
//                        openDialog = false
//                    },
//                    addRecipe = { name, shortDesc ->
//                        viewModel.addRecipe(
//                            recipe = RecipeModel(
//                                name = name, shortDesc = shortDesc
//                            )
//                        )
//                    }
//                )
//            }
//
//        },
//        floatingActionButton = {
//            AddRecipeActionButton(
//                openDialog = {
//                    openDialog = true
//                }
//            )
//        }
//    )
//    AddRecipe()
}
