package com.example.cookbook

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.presentation.view.homeScreen.BottomNavBarWithFab
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun MainScreen (
    @ApplicationContext context: Context
) {
    val navHostController: NavHostController = rememberNavController()
    BottomNavBarWithFab(navHostController = navHostController, context)
}