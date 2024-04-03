package com.example.cookbook.presentation.view.launchScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cookbook.R
import com.example.cookbook.navigation.Screen
import com.example.cookbook.presentation.view.launchScreen.components.BoxGradient
import com.example.cookbook.ui.theme.PrimaryRed50
import com.example.cookbook.ui.theme.Typography

@Composable
fun LaunchScreen(
    navController: NavHostController,
    viewModel: LaunchViewModel = hiltViewModel()
) {

    Box() {
        Image(
            painter = painterResource(id = R.drawable.intro_background),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    viewModel.image = it.size
                }
        )
        BoxGradient(image = viewModel.image)
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = stringResource(id = R.string.lets),
                style = Typography.headlineLarge,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.cooking),
                style = Typography.headlineLarge,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.find_best_recipies),
                style = Typography.bodyMedium,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = {
                    if (viewModel.checkForAuthUser()) {
                        navController.navigate(Screen.Home.route)
                    } else {
                        navController.navigate(Screen.AuthChoice.route)
                    }
                },
                shape = RectangleShape,
                modifier = Modifier
                    .padding(40.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(PrimaryRed50)
            ) {
                Text(
                    text = stringResource(id = R.string.start_cooking),
                    style = Typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp, start = 16.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(end = 16.dp, start = 8.dp)
                        .size(20.dp)
                )
            }
        }
    }
}
