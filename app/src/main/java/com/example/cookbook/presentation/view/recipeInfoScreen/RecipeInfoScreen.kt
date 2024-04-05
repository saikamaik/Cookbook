package com.example.cookbook.presentation.view.recipeInfoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.cookbook.R
import com.example.cookbook.presentation.view.common.TextHeader
import com.example.cookbook.presentation.view.common.TextHeader4
import com.example.cookbook.presentation.view.common.TextMedium
import com.example.cookbook.ui.theme.PrimaryRed50
import com.example.cookbook.ui.theme.TertiaryGray10
import com.example.cookbook.ui.theme.TertiaryGray30
import com.example.cookbook.ui.theme.TertiaryGray70
import com.example.cookbook.ui.theme.TertiaryGray90
import kotlin.time.Duration.Companion.seconds

@Composable
fun RecipeInfoScreen(
    navController: NavHostController
) {

    val viewModel: RecipeInfoViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()

    val configuration = LocalConfiguration.current
    val imageHeight = (configuration.screenHeightDp / 2).dp

    LazyColumn(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 16.dp)
    ) {
        item {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        navController.navigateUp()
                    }
                    .size(24.dp)
            )
            TextHeader4(
                header = uiState.value.recipe.name,
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 16.dp)
            )
            AsyncImage(
                model = uiState.value.recipe.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
        }

        item {

            if (uiState.value.recipe.userUid.isNotEmpty()) {

                TextHeader(
                    header = "Creator",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    AsyncImage(
                        model = uiState.value.user.imageUrl?.toUri(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clip(RoundedCornerShape(50))
                            .size(60.dp, 60.dp)
                            .background(TertiaryGray10)
                    )
                    TextMedium(
                        text = uiState.value.user.username,
                        color = TertiaryGray90,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }

            Column(
                modifier = Modifier.padding(bottom = 12.dp)
            ) {

                TextHeader(
                    header = stringResource(id = R.string.description),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                TextMedium(
                    text = uiState.value.recipe.description.toString(),
                    color = TertiaryGray70
                )

                Row(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp)
                        .background(TertiaryGray10, RoundedCornerShape(10.dp))
                        .padding(top = 12.dp, bottom = 12.dp, start = 12.dp, end = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_clock),
                        contentDescription = null,
                        tint = PrimaryRed50,
                        modifier = Modifier
                            .padding(4.dp)
                            .background(Color.White, shape = RoundedCornerShape(5.dp))
                            .padding(4.dp)
                    )
                    TextMedium(
                        text = stringResource(id = R.string.cook_time),
                        color = null,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    TextMedium(
                        text = uiState.value.recipe.cookTime?.seconds?.inWholeMinutes.toString() + " min",
                        color = TertiaryGray30
                    )
                }

                Row(
                    modifier = Modifier
                        .background(TertiaryGray10, RoundedCornerShape(10.dp))
                        .padding(top = 12.dp, bottom = 12.dp, start = 12.dp, end = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_clock),
                        contentDescription = null,
                        tint = PrimaryRed50,
                        modifier = Modifier
                            .padding(4.dp)
                            .background(Color.White, shape = RoundedCornerShape(5.dp))
                            .padding(4.dp)
                    )
                    TextMedium(
                        text = stringResource(id = R.string.type_recipe),
                        color = null,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    TextMedium(
                        text = uiState.value.recipe.type.toString(),
                        color = TertiaryGray30
                    )
                }
            }

            Row(
            ) {

                TextHeader(
                    header = stringResource(id = R.string.ingredients),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                TextMedium(
                    text = uiState.value.recipe.ingredientsList.size.toString() + " items",
                    color = TertiaryGray30
                )

            }

        }

        items(uiState.value.recipe.ingredientsList) {
            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .background(TertiaryGray10, RoundedCornerShape(10.dp))
                    .padding(top = 12.dp, bottom = 12.dp, start = 12.dp, end = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextMedium(
                    text = it.name.toString(),
                    fontWeight = FontWeight.SemiBold,
                    color = TertiaryGray90
                )
                Spacer(modifier = Modifier.weight(1f))
                TextMedium(
                    text = it.amount.toString(),
                    color = TertiaryGray30
                )
            }

        }

        item {
            Row {
                TextHeader(
                    header = stringResource(id = R.string.steps),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                TextMedium(
                    text = uiState.value.recipe.stepsList.size.toString() + " steps",
                    color = TertiaryGray30
                )

            }

        }

        itemsIndexed(uiState.value.recipe.stepsList) { index, item ->

            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .background(TertiaryGray10, RoundedCornerShape(10.dp))
                    .padding(top = 12.dp, bottom = 12.dp, start = 12.dp, end = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextMedium(
                    text = "Step " + (index + 1) + ". ",
                    fontWeight = FontWeight.SemiBold,
                    color = TertiaryGray90
                )
                TextMedium(
                    text = item,
                    color = TertiaryGray70
                )
            }
        }

    }
}