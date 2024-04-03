package com.example.cookbook.presentation.view.authScreens.signUpScreen

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cookbook.R
import com.example.cookbook.ui.theme.TertiaryGray10
import com.example.cookbook.ui.theme.TertiaryGray70

@Composable
fun UserPhotoPickerBox(
    selectedImageUri: Uri?,
    onClick: () -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    if (selectedImageUri != null) {
        Box(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(50))
                .height(150.dp)
                .width(150.dp)
                .background(TertiaryGray10)
        ) {
            AsyncImage(
                model = selectedImageUri,
                contentDescription = "",
                clipToBounds = true,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = interactionSource,
                        null
                    ) {
                        onClick()
                    }
            )
        }
    } else {
        Box(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(50))
                .height(150.dp)
                .width(150.dp)
                .background(TertiaryGray10)
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