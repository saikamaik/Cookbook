package com.example.cookbook.presentation.view.common.tabs

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.cookbook.presentation.view.homeScreen.HomeViewModel
import com.example.cookbook.presentation.view.homeScreen.uiEvent.HomeUiEvent
import com.example.cookbook.ui.theme.PrimaryRed30
import com.example.cookbook.ui.theme.PrimaryRed50

@Composable
fun TabButtonBar(
    labels: List<String>,
    selectedOption: String,
    viewModel: HomeViewModel
) {

    Row( // Tab'ы
        modifier = Modifier
            .horizontalScroll(
                rememberScrollState()
            )
    ) {
        labels.forEach { text ->
            TabButton(
                text = text,
                onClick = {
                    viewModel.postUiEvent(HomeUiEvent.ChangeSelectedTabOption(text))
                },
                color = if (text == selectedOption) {
                    PrimaryRed50
                } else {
                    Color.Transparent
                },
                textColor = if (text == selectedOption) {
                    Color.White
                } else {
                    PrimaryRed30
                },
                fontWeight = FontWeight.Bold
            )
        }
    }

}