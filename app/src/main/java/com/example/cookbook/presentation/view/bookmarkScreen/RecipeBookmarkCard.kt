package com.example.cookbook.presentation.view.bookmarkScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cookbook.R
import com.example.cookbook.data.model.RecipeModel
import com.example.cookbook.ui.theme.TertiaryGray10
import com.example.cookbook.ui.theme.TertiaryGray90
import com.example.cookbook.ui.theme.Typography

@Composable
fun RecipeBookmarkCard(
    recipe: RecipeModel,
    onClick: () -> Unit
) {

    Column(

    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .height(200.dp)
                .background(TertiaryGray10)
        ) {

            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = "",
                clipToBounds = true,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_bottomnav_bookmark_active),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(32.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(50.dp))
                    .padding(6.dp)
                    .clickable {
                        onClick()
                    }
            )

        }

        Text(
            text = recipe.name,
            style = Typography.bodyMedium,
            color = TertiaryGray90,
            modifier = Modifier.align(Alignment.Start)
        )

    }

}