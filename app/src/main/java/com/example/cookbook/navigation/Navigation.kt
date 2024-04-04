package com.example.cookbook.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cookbook.presentation.view.authScreens.authChoiceScreen.AuthChoiceScreen
import com.example.cookbook.presentation.view.authScreens.signInScreen.SignInScreen
import com.example.cookbook.presentation.view.authScreens.signUpScreen.SignUpScreen
import com.example.cookbook.presentation.view.bookmarkScreen.BookMarkScreen
import com.example.cookbook.presentation.view.createRecipeScreen.CreateRecipeScreen
import com.example.cookbook.presentation.view.homeScreen.HomeScreen
import com.example.cookbook.presentation.view.launchScreen.LaunchScreen
import com.example.cookbook.presentation.view.profileScreen.ProfileScreen
import com.example.cookbook.presentation.view.recipeInfoScreen.RecipeInfoScreen
import com.example.cookbook.presentation.view.searchScreen.SearchScreen
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun Navigation(
    navController: NavHostController,
    @ApplicationContext context: Context
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Launch.route
    ) {

        composable(Screen.Launch.route) {
            LaunchScreen(
                navController
            )
        }
        composable(Screen.AuthChoice.route) {
            AuthChoiceScreen(
                navController
            )
        }
        composable(Screen.SignIn.route) {
            SignInScreen(
                navController,
                context
            )
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(
                navController, context
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                navController
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen(
                navController
            )
        }
        composable(Screen.Bookmark.route) {
            BookMarkScreen(
            )
        }
        composable(Screen.CreateRecipe.route) {
            CreateRecipeScreen(
                navController
            )
        }
        composable(Screen.Search.route) {
            SearchScreen(
                navController
            )
        }

        composable(
            Screen.RecipeInfo.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) {
            RecipeInfoScreen(
                navController
            )
        }
    }
}