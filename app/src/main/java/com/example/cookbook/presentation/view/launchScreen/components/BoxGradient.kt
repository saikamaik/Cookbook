package com.example.cookbook.presentation.view.launchScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

@Composable
fun BoxGradient(image: IntSize) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                //Градиент снизу экрана
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black
                    ),
                    startY = image.height.toFloat() / 2,
                    endY = image.height.toFloat()
                )
            )
    )
}