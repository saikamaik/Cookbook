package com.example.cookbook.presentation.view.profileScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.cookbook.R
import com.example.cookbook.navigation.Screen
import com.example.cookbook.presentation.view.common.TextHeader4
import com.example.cookbook.presentation.view.common.recipe.Recipes
import com.example.cookbook.presentation.view.profileScreen.components.UserRecipeContent
import com.example.cookbook.ui.theme.PrimaryRed50
import com.example.cookbook.ui.theme.TertiaryGray10
import com.example.cookbook.ui.theme.TertiaryGray30
import com.example.cookbook.ui.theme.TertiaryGray90
import com.example.cookbook.ui.theme.Typography

@Composable
fun ProfileScreen(
    navHostController: NavHostController
) {

    val viewModel: ProfileViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()

    Column {

        Column(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)
        ) {

            TextHeader4(
                header = stringResource(id = R.string.my_profile),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 20.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                AsyncImage(
                    model = uiState.value.user?.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(140.dp)
                        .width(140.dp)
                        .clip(CircleShape)
                        .background(TertiaryGray10),
                )
                Spacer(modifier = Modifier.weight(1f))
                OutlinedButton(
                    border = BorderStroke(1.dp, SolidColor(PrimaryRed50)),
                    modifier = Modifier.align(Alignment.CenterVertically),
                    onClick = {
                        /*TODO открыть экран настроек с изменением всех полей*/
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.edit_profile),
                        style = Typography.labelSmall,
                        color = PrimaryRed50
                    )
                }
            }

            uiState.value.user?.username?.let {
                Text(
                    text = it,
                    style = Typography.labelMedium,
                    color = TertiaryGray90,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = TertiaryGray30,
            modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)
        )

        Recipes(
            uiState.value.recipeResponse,
            recipeContent = { recipes ->
                UserRecipeContent(
                    recipes = recipes,
                    navHostController = navHostController
                ) {
                    navHostController.navigate(Screen.RecipeInfo.route + "/${it}")
                }
            }
        )
    }
}
