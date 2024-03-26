package com.example.cookbook.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cookbook.presentation.view.createRecipeScreen.CreateRecipeScreen
import com.example.cookbook.presentation.view.homeScreen.HomeScreen
import com.example.cookbook.presentation.view.introScreen.LaunchScreen

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Launch.route
    ) {
        composable(Screen.Launch.route) {
            LaunchScreen(
                navController
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
            )
        }
        composable(
            Screen.RecipeInfo.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
//            ItemInfoScreen(
//                navController
//            )
        }
        composable(Screen.CreateRecipe.route) {
            CreateRecipeScreen(
                navController
            )
        }
    }
}