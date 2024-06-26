package com.example.cookbook.presentation.view.createRecipeScreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.cookbook.R
import com.example.cookbook.data.model.Ingredient
import com.example.cookbook.presentation.view.createRecipeScreen.CreateRecipeViewModel
import com.example.cookbook.presentation.view.createRecipeScreen.createRecipeUiEvent.CreateRecipeUiEvent
import com.example.cookbook.presentation.view.createRecipeScreen.createRecipeUiState.CreateRecipeUiState
import com.example.cookbook.ui.theme.PrimaryRed50
import com.example.cookbook.ui.theme.TertiaryGray30
import com.example.cookbook.ui.theme.TertiaryGray90
import com.example.cookbook.ui.theme.Typography

@Composable
fun IngredientTextField(
    ingredient: Ingredient,
    viewModel: CreateRecipeViewModel,
    uiState: CreateRecipeUiState
) {

    var nameTextField by remember {
        mutableStateOf(ingredient.name)
    }
    var amountTextField by remember {
        mutableStateOf(ingredient.amount)
    }

    Row(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
    ) {
        nameTextField?.let {
            OutlinedTextField(
                value = it,
                onValueChange = {
                    nameTextField = it
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.item_name),
                        style = Typography.labelSmall,
                        color = TertiaryGray30
                    )
                },
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = PrimaryRed50,
                    unfocusedBorderColor = TertiaryGray30,
                    focusedTextColor = TertiaryGray90,
                ),
                textStyle = Typography.bodyMedium,
                singleLine = true,
                modifier = Modifier
                    .weight(3f)
                    .padding(end = 12.dp)
            )
        }

        amountTextField?.let {
            OutlinedTextField(
                value = it,
                onValueChange = {
                    amountTextField = it
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.amount),
                        style = Typography.labelSmall,
                        color = TertiaryGray30
                    )
                },
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedBorderColor = PrimaryRed50,
                    unfocusedBorderColor = TertiaryGray30,
                    focusedTextColor = TertiaryGray90,
                ),
                textStyle = Typography.bodyMedium,
                singleLine = true,
                modifier = Modifier
                    .weight(2f)
                    .padding(end = 12.dp)
            )
        }

        if (uiState.listOfIngredients.contains(
                Ingredient(
                    nameTextField, amountTextField
                )
            )
        ) {
            IconButton(
                onClick = {
                    viewModel.postUiEvent(
                        CreateRecipeUiEvent.DeleteFromIngredientList(
                            Ingredient(
                                nameTextField,
                                amountTextField
                            )
                        )
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_minus_border),
                    contentDescription = null,
                    tint = TertiaryGray90,
                    modifier = Modifier.size(24.dp)
                )
            }
        } else {
            IconButton(
                onClick = {
                    viewModel.postUiEvent(
                        CreateRecipeUiEvent.AddToIngredientList(
                            Ingredient(
                                nameTextField, amountTextField
                            )
                        )
                    )
                    viewModel.postUiEvent(
                        CreateRecipeUiEvent.DeleteFromIngredientList(
                            Ingredient(
                                "",
                                ""
                            )
                        )
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus_border),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }

}