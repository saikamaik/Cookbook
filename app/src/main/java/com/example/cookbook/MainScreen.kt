package com.example.cookbook

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.presentation.view.homeScreen.BottomNavBarWithFab

@Composable
fun MainScreen (
) {
    val navHostController: NavHostController = rememberNavController()
    BottomNavBarWithFab(navHostController = navHostController)
}