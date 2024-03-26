package com.example.cookbook.presentation.view.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.cookbook.presentation.view.createRecipeScreen.CreateRecipeViewModel
import com.example.cookbook.presentation.view.createRecipeScreen.createRecipeUiEvent.CreateRecipeUiEvent
import com.example.cookbook.presentation.view.createRecipeScreen.createRecipeUiState.CreateRecipeUiState
import com.example.cookbook.ui.theme.Typography

@Composable
fun TypeDropDownMenu(
    uiState: CreateRecipeUiState,
    viewModel: CreateRecipeViewModel
) {

    val configuration = LocalConfiguration.current
    val textfieldSize = configuration.screenWidthDp.dp / 2

    val typeList: List<String> = listOf(
        "Salad", "Breakfast", "Lunch", "Appetizer", "Dessert"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    viewModel.postUiEvent(CreateRecipeUiEvent.ExpandDropDownMenu)
                }
        ) {
            Text(
                text = uiState.selectedMenuItem,
                style = Typography.labelSmall
            )
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = "",
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.Bottom)
            )
        }

        DropdownMenu(
            expanded = uiState.isDropDownMenuExpanded,
            onDismissRequest = {
                viewModel.postUiEvent(CreateRecipeUiEvent.ExpandDropDownMenu)
            },
            modifier = Modifier.width(textfieldSize)
        ) {
            typeList.forEach { type ->
                DropdownMenuItem(
                    onClick = {
                        viewModel.postUiEvent(CreateRecipeUiEvent.ExpandDropDownMenu)
                        viewModel.postUiEvent(CreateRecipeUiEvent.ChangeSelectedMenuItem(type))
                    },
                    text = {
                        Text(
                            text = type,
                            style = Typography.labelSmall,
                            color = Color.Black
                        )
                    }
                )
            }
        }
    }

}