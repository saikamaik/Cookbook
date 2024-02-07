package com.example.cookbook.presentation.view.recipe.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeDialog(
    closeDialog: () -> Unit,
    addRecipe: (name: String, shortDesc: String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var shortDesc by remember { mutableStateOf("") }
    val focusRequester = FocusRequester()

    AlertDialog(
        onDismissRequest = closeDialog,
        title = {
            Text(
                text = "Добавить Рецепт"
            )
        },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = {
                        Text(
                            text = "Название рецепта"
                        )
                    },
                    modifier = Modifier.focusRequester(focusRequester)
                )
                LaunchedEffect(Unit) {
                    coroutineContext.job.invokeOnCompletion {
                        focusRequester.requestFocus()
                    }
                }
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                TextField(
                    value = shortDesc,
                    onValueChange = { shortDesc = it },
                    placeholder = {
                        Text(
                            text = "Краткое описание"
                        )
                    }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    closeDialog()
                    addRecipe(name, shortDesc)
                }
            ) {
                Text(
                    text = "Добавить"
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = closeDialog
            ) {
                Text(
                    text = "Отменить"
                )
            }
        }
    )
}