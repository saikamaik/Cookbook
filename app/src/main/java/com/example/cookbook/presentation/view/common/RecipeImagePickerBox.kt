package com.example.cookbook.presentation.view.common

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cookbook.R
import com.example.cookbook.ui.theme.PrimaryRed50
import com.example.cookbook.ui.theme.TertiaryGray10
import com.example.cookbook.ui.theme.TertiaryGray70

@Composable
fun RecipeImagePickerBox(
    selectedImageUri: Uri?,
    onClick: () -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    if (selectedImageUri != null) {
        Box(
            modifier = Modifier
                .padding(top = 12.dp, bottom = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .height(200.dp)
                .background(TertiaryGray10)
        ) {
            AsyncImage(
                model = selectedImageUri,
                contentDescription = "",
                clipToBounds = true,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = null,
                tint = PrimaryRed50,
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
    } else {
        Box(
            modifier = Modifier
                .padding(top = 12.dp, bottom = 16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(TertiaryGray10)
                .fillMaxWidth()
                .height(200.dp)
                .clickable(
                    interactionSource = interactionSource,
                    null
                ) {
                    onClick()
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_photo_camera),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(50.dp),
                tint = TertiaryGray70
            )
        }

    }
}