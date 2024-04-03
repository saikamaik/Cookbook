package com.example.cookbook.presentation.view.authScreens.authChoiceScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cookbook.navigation.Screen
import com.example.cookbook.ui.theme.NoRippleTheme
import com.example.cookbook.ui.theme.PrimaryRed30
import com.example.cookbook.ui.theme.PrimaryRed50
import com.example.cookbook.ui.theme.Typography

@Composable
fun AuthChoiceScreen(
    navController: NavHostController,
    viewModel: AuthChoiceViewModel = hiltViewModel()
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
            Button(
                onClick = {
                    navController.navigate(Screen.SignIn.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryRed50,
                    contentColor = Color.White
                ),
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Text(
                    text = "Sign In",
                    style = Typography.bodyMedium
                )
            }

            TextButton(
                onClick = {
                    navController.navigate(Screen.SignUp.route)
                },
                modifier = Modifier.padding(bottom = 12.dp)
            ) {

                Text(
                    text = "Sign Up",
                    style = Typography.bodyMedium,
                    color = PrimaryRed50
                )
            }

            TextButton(
                onClick = {
                    viewModel.signInAnon()
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.AuthChoice.route) {
                            inclusive = true
                        }
                    }
                }
            ) {
                Text(
                    text = "Sign In as Guest",
                    style = Typography.bodyMedium,
                    color = PrimaryRed30
                )
            }

        }
    }
}
