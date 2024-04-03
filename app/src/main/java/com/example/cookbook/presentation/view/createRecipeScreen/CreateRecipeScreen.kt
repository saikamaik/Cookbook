package com.example.cookbook.presentation.view.createRecipeScreen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cookbook.R
import com.example.cookbook.data.model.Ingredient
import com.example.cookbook.data.model.RecipeModel
import com.example.cookbook.navigation.Screen
import com.example.cookbook.presentation.view.common.ErrorText
import com.example.cookbook.presentation.view.common.recipe.RecipeImagePickerBox
import com.example.cookbook.presentation.view.common.TypeDropDownMenu
import com.example.cookbook.presentation.view.createRecipeScreen.components.IngredientTextField
import com.example.cookbook.presentation.view.createRecipeScreen.components.StepsTextField
import com.example.cookbook.presentation.view.createRecipeScreen.createRecipeUiEvent.CreateRecipeUiEvent
import com.example.cookbook.presentation.view.common.TextHeader
import com.example.cookbook.presentation.view.common.checkRecipeForError
import com.example.cookbook.ui.theme.PrimaryRed50
import com.example.cookbook.ui.theme.TertiaryGray10
import com.example.cookbook.ui.theme.TertiaryGray30
import com.example.cookbook.ui.theme.TertiaryGray90
import com.example.cookbook.ui.theme.Typography
import com.maxkeppeler.sheets.duration.DurationDialog
import com.maxkeppeler.sheets.duration.models.DurationConfig
import com.maxkeppeler.sheets.duration.models.DurationFormat
import com.maxkeppeler.sheets.duration.models.DurationSelection
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRecipeScreen(
    navController: NavHostController,
    viewModel: CreateRecipeViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val focusRequester = FocusRequester()

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        selectedImageUri = uri
    }

    fun onClick() {
        singlePhotoPickerLauncher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }

    LazyColumn(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            navController.navigateUp()
                        }
                        .size(24.dp)
                        .align(Alignment.CenterVertically))
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    onClick = {
                        checkRecipeForError(
                            RecipeModel(
                                name = uiState.recipeNameTextFieldValue,
                                description = uiState.recipeDescTextFieldValue,
                                ingredientsList = uiState.listOfIngredients,
                                stepsList = uiState.listOfSteps,
                                cookTime = uiState.selectedTimeInSeconds,
                                type = uiState.selectedMenuItem,
                                imageUrl = selectedImageUri.toString()
                            ),
                            viewModel
                        )
                        if (
                            viewModel.uiState.value.isError
                        ) {
                            viewModel.postUiEvent(CreateRecipeUiEvent.ChangeErrorValue("Input cannot be empty"))
                            viewModel.postUiEvent(CreateRecipeUiEvent.ChangeErrorStatus(false))
                        } else {
                            viewModel.postUiEvent(CreateRecipeUiEvent.ChangeErrorValue(""))
                            selectedImageUri?.let {
                                viewModel.addPhotoToFirebaseStorage(
                                    uri = it, RecipeModel(
                                        name = uiState.recipeNameTextFieldValue,
                                        description = uiState.recipeDescTextFieldValue,
                                        ingredientsList = uiState.listOfIngredients,
                                        stepsList = uiState.listOfSteps,
                                        cookTime = uiState.selectedTimeInSeconds,
                                        type = uiState.selectedMenuItem
                                    )
                                )
                            }
                            navController.navigate(Screen.Home.route)
                        }
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.save),
                        style = Typography.labelSmall,
                        color = PrimaryRed50,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }

            TextHeader(
                header = stringResource(id = R.string.create_recipe),
                modifier = Modifier
            )
            RecipeImagePickerBox(
                selectedImageUri = selectedImageUri,
                onClick = { onClick() }
            )

            OutlinedTextField(
                value = uiState.recipeNameTextFieldValue,
                onValueChange = {
                    viewModel.postUiEvent(CreateRecipeUiEvent.ChangeNameTextValue(it))
                },
                isError = uiState.errorText.isNotEmpty(),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.recipe_name),
                        style = Typography.labelSmall
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryRed50,
                    unfocusedBorderColor = PrimaryRed50,
                    focusedTextColor = TertiaryGray90,
                    disabledPlaceholderColor = TertiaryGray30,
                    unfocusedPlaceholderColor = TertiaryGray30
                ),
                textStyle = Typography.bodyMedium,
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .fillMaxWidth()
            )

            ErrorText(
                errorText = uiState.errorText,
                textForCheck = listOf(uiState.recipeNameTextFieldValue),
                modifier = Modifier.padding(bottom = 6.dp, top = 4.dp)
            )

            OutlinedTextField(
                value = uiState.recipeDescTextFieldValue,
                onValueChange = {
                    viewModel.postUiEvent(CreateRecipeUiEvent.ChangeDescTextValue(it))
                },
                isError = uiState.errorText.isNotEmpty(),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.description),
                        style = Typography.labelSmall
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryRed50,
                    unfocusedBorderColor = PrimaryRed50,
                    focusedTextColor = TertiaryGray90,
                    disabledPlaceholderColor = TertiaryGray30,
                    unfocusedPlaceholderColor = TertiaryGray30
                ),
                textStyle = Typography.bodyMedium,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            ErrorText(
                errorText = uiState.errorText,
                textForCheck = listOf(uiState.recipeDescTextFieldValue),
                modifier = Modifier.padding(bottom = 6.dp, top = 4.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 20.dp)
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
                Text(
                    text = "Cook Time",
                    style = Typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = uiState.selectedTimeInSeconds.seconds.inWholeMinutes.toString() + " min",
                    style = Typography.bodyMedium,
                    color = TertiaryGray30
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = null,
                    tint = TertiaryGray90,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable {
                            uiState.timePickerState.show()
                        })
                DurationDialog(
                    state = uiState.timePickerState,
                    selection = DurationSelection { newTimeInSeconds ->
                        viewModel.postUiEvent(
                            CreateRecipeUiEvent.ChangeSelectedTime(
                                newTimeInSeconds
                            )
                        )
                    },
                    config = DurationConfig(
                        timeFormat = DurationFormat.HH_MM_SS,
                        currentTime = uiState.selectedTimeInSeconds
                    ),
                )
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 20.dp)
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
                Text(
                    text = "Type of Recipe",
                    style = Typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.weight(1f))
                TypeDropDownMenu(uiState = uiState, viewModel = viewModel)
            }

            TextHeader(
                header = stringResource(id = R.string.ingredients),
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        items(uiState.listOfIngredients) {
            IngredientTextField(it, viewModel = viewModel, uiState = uiState)
        }

        item {
            TextButton(
                onClick = {
                    viewModel.postUiEvent(
                        CreateRecipeUiEvent.AddToIngredientList(
                            Ingredient(
                                "",
                                ""
                            )
                        )
                    )
                },
                modifier = Modifier
                    .padding(bottom = 12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = null,
                    tint = TertiaryGray90,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 4.dp)
                )
                Text(
                    text = stringResource(id = R.string.add_new_ingredient),
                    style = Typography.bodyMedium,
                    color = TertiaryGray90
                )
            }

        }

        item {
            ErrorText(
                errorText = uiState.errorText,
                textForCheck = uiState.listOfIngredients.map {
                    it.name
                    it.amount
                },
                modifier = Modifier
            )
        }

        item {
            TextHeader(
                header = stringResource(id = R.string.steps),
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        items(uiState.listOfSteps) {
            StepsTextField(it, viewModel = viewModel, uiState = uiState)
        }

        item {
            TextButton(
                onClick = {
                    viewModel.postUiEvent(
                        CreateRecipeUiEvent.AddToStepList("")
                    )
                },
                modifier = Modifier
                    .padding(bottom = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = null,
                    tint = TertiaryGray90,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 4.dp)
                )
                Text(
                    text = stringResource(id = R.string.add_new_step),
                    style = Typography.bodyMedium,
                    color = TertiaryGray90
                )
            }
        }

        item {
            ErrorText(
                errorText = uiState.errorText,
                textForCheck = uiState.listOfSteps,
                modifier = Modifier
            )
        }

    }
}
