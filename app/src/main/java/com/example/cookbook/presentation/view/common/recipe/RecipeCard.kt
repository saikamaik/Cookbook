package com.example.cookbook.presentation.view.common.recipe

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.cookbook.R
import com.example.cookbook.data.model.RecipeModel
import com.example.cookbook.navigation.Screen
import com.example.cookbook.ui.theme.TertiaryGray10
import com.example.cookbook.ui.theme.TertiaryGray30
import com.example.cookbook.ui.theme.TertiaryGray90
import com.example.cookbook.ui.theme.Typography
import kotlin.time.Duration.Companion.seconds

@SuppressLint("SuspiciousIndentation")
@Composable
fun RecipeCard(
    recipeModel: RecipeModel,
    navController: NavHostController,
    onClick: (String) -> Unit
) {

    val recipeName: String = if (
        recipeModel.name.length > 18
    ) {
        recipeModel.name.slice(0..16) + ".."
    } else {
        recipeModel.name
    }

    val configuration = LocalConfiguration.current
    val cardWidth = (configuration.screenWidthDp / 2.2).dp
    val cardHeight = configuration.screenHeightDp.dp / 3

    Column(
        modifier = Modifier
            .width(cardWidth)
            .clip(RoundedCornerShape(12))
            .background(TertiaryGray10)
            .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 12.dp)
            .clickable {
                navController.navigate(Screen.RecipeInfo.route + "/${recipeModel.id}")
            }
    ) {

        AsyncImage(
            model = recipeModel.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(12))
                .size(width = cardWidth - 10.dp, height = (cardHeight / 2))
                .background(TertiaryGray30)
                .align(Alignment.CenterHorizontally)
        )

        Column(
            verticalArrangement = Arrangement.Bottom
        ) {

            Text(
                text = recipeName,
                style = Typography.labelSmall,
                color = TertiaryGray90,
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 12.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(start = 6.dp, end = 6.dp)
            ) {

                Column {
                    Text(
                        text = "Time", style = Typography.bodySmall, color = TertiaryGray30
                    )
                    Text(
                        text = recipeModel.cookTime?.seconds?.inWholeMinutes.toString() + " Mins",
                        style = Typography.bodySmall,
                        color = TertiaryGray90
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    painter = painterResource(id = R.drawable.ic_bottomnav_bookmark_inactive),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(26.dp)
                        .background(color = Color.White, shape = CircleShape)
                        .padding(6.dp)
                        .clickable {
                            onClick(recipeModel.id)
                        }
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}