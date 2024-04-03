package com.example.cookbook.presentation.view.homeScreen

//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cookbook.navigation.Navigation
import com.example.cookbook.navigation.Screen
import com.example.cookbook.ui.theme.PrimaryRed50
import dagger.hilt.android.qualifiers.ApplicationContext

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavBarWithFab(
    navHostController: NavHostController,
    @ApplicationContext context: Context
) {

    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()

    showBottomBar = when (navBackStackEntry?.destination?.route) {
        Screen.Launch.route -> false
        Screen.CreateRecipe.route -> false
        Screen.AuthChoice.route -> false
        Screen.SignIn.route -> false
        Screen.SignUp.route -> false
        else -> true
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) MyNavigationBar(
                modifier = Modifier,
                navHostController = navHostController
            )
        }
    )
    {
        Navigation(navHostController, context)
    }
}


@Composable
fun MyNavigationBar(modifier: Modifier, navHostController: NavHostController) {

    val screen: List<Screen> = listOf(Screen.Home, Screen.Bookmark, Screen.Search, Screen.Profile)

    NavigationBar(
        modifier = modifier,
        containerColor = Color.White,
        tonalElevation = 10.dp
    ) {

        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        screen.forEachIndexed { index, screen ->
            val selected =
                navBackStackEntry?.destination?.hierarchy?.any { it.route == screen.route } == true

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navHostController.navigate(screen.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = (((if (selected) screen.icon_active else screen.icon_inactive)!!))),
                        contentDescription = null,
                        Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )

            if (index == 1) {
                FloatingActionButton(
                    onClick = {
                        navHostController.navigate(Screen.CreateRecipe.route)
                    },
                    shape = RoundedCornerShape(50),
                    containerColor = PrimaryRed50,
                ) {
                    Icon(Icons.Filled.Add, tint = Color.White, contentDescription = "Add")
                }
            }
        }


    }
}

