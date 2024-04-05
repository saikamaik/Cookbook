package com.example.cookbook.presentation.view.authScreens.signUpScreen

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cookbook.R
import com.example.cookbook.navigation.Screen
import com.example.cookbook.presentation.view.authScreens.signUpScreen.signUpUiEvent.SignUpUiEvent
import com.example.cookbook.presentation.view.common.ErrorText
import com.example.cookbook.presentation.view.common.TextMedium
import com.example.cookbook.ui.theme.NoRippleTheme
import com.example.cookbook.ui.theme.PrimaryRed50
import com.example.cookbook.ui.theme.TertiaryGray30
import com.example.cookbook.ui.theme.Typography
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun SignUpScreen(
    navController: NavHostController,
    @ApplicationContext context: Context
) {

    val viewModel: SignUpViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            viewModel.postUiEvent(SignUpUiEvent.ChangeSelectedImageUri(uri))
        }
    }

    fun onClick() {
        singlePhotoPickerLauncher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ) {

        Text(
            text = stringResource(id = R.string.sign_up),
            style = Typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = PrimaryRed50,
            modifier = Modifier
                .padding(bottom = 18.dp)
                .align(Alignment.CenterHorizontally)
        )

        UserPhotoPickerBox(
            selectedImageUri = uiState.value.selectedImageUri,
            onClick = { onClick() }
        )

        OutlinedTextField(
            value = uiState.value.emailTextFieldValue,
            onValueChange = {
                viewModel.postUiEvent(SignUpUiEvent.ChangeEmailTextValue(it))
            },
            isError = uiState.value.isError,
            textStyle = Typography.bodyMedium,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = null,
                    tint = TertiaryGray30
                )
            },
            placeholder = {
                TextMedium(
                    text = stringResource(id = R.string.email),
                    color = TertiaryGray30
                )
            },
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .padding(bottom = 6.dp)
                .fillMaxWidth()
        )

        ErrorText(
            errorText = uiState.value.errorText,
            textForCheck = listOf(uiState.value.emailTextFieldValue),
            modifier = Modifier.padding(top = 4.dp)
        )

        OutlinedTextField(
            value = uiState.value.userNameTextFieldValue,
            onValueChange = {
                viewModel.postUiEvent(SignUpUiEvent.ChangeUserNameTextValue(it))
            },
            isError = uiState.value.isError,
            textStyle = Typography.bodyMedium,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Face,
                    contentDescription = null,
                    tint = TertiaryGray30
                )
            },
            placeholder = {
                TextMedium(
                    text = stringResource(id = R.string.username),
                    color = TertiaryGray30
                )
            },
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .padding(bottom = 6.dp, top = 6.dp)
                .fillMaxWidth()
        )

        ErrorText(
            errorText = uiState.value.errorText,
            textForCheck = listOf(uiState.value.userNameTextFieldValue),
            modifier = Modifier.padding(top = 4.dp)
        )

        OutlinedTextField(
            value = uiState.value.passwordTextFieldValue,
            onValueChange = {
                viewModel.postUiEvent(SignUpUiEvent.ChangePasswordTextValue(it))
            },
            visualTransformation = if (uiState.value.passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            isError = uiState.value.isError,
            textStyle = Typography.bodyMedium,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = null,
                    tint = TertiaryGray30
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        viewModel.postUiEvent(SignUpUiEvent.ChangePasswordVisibility)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_visibility_eye),
                        contentDescription = null,
                        tint = TertiaryGray30
                    )
                }
            },
            placeholder = {
                TextMedium(
                    text = stringResource(id = R.string.password),
                    color = TertiaryGray30
                )
            },
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .padding(bottom = 6.dp, top = 6.dp)
                .fillMaxWidth()
        )

        ErrorText(
            errorText = uiState.value.errorText,
            textForCheck = listOf(uiState.value.passwordTextFieldValue),
            modifier = Modifier.padding(top = 4.dp)
        )

        CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
            Button(
                onClick = {
                    if (viewModel.validateCredentials(
                            uiState.value.emailTextFieldValue,
                            uiState.value.passwordTextFieldValue,
                            uiState.value.userNameTextFieldValue
                        )
                    ) {
                        viewModel.postUiEvent(
                            SignUpUiEvent.SignUpWithEmail(
                                uiState.value.emailTextFieldValue,
                                uiState.value.passwordTextFieldValue,
                                uiState.value.userNameTextFieldValue
                            )
                        )
                        if (viewModel.uiState.value.signUpResponse.isEmpty() && viewModel.uiState.value.signUpResponse.isBlank()) {
                            uiState.value.selectedImageUri?.let {
                                viewModel.addPhotoToFirebaseStorage(
                                    it
                                )
                            }
                            viewModel.postUiEvent(SignUpUiEvent.ChangeErrorStatus(false))
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.SignUp.route) {
                                    inclusive = true
                                }
                            }
                        } else {
                            Toast.makeText(
                                context,
                                viewModel.uiState.value.signUpResponse.substringBefore("."),
                                Toast.LENGTH_SHORT
                            ).show()
                            viewModel.postUiEvent(SignUpUiEvent.ChangeSignUpResponse(""))
                        }
                    } else {
                        viewModel.postUiEvent(SignUpUiEvent.ChangeErrorStatus(true))
                        viewModel.postUiEvent(
                            SignUpUiEvent.ChangeErrorValue(
                                getString(
                                    context,
                                    R.string.input_empty
                                )
                            )
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryRed50
                )
            ) {
                TextMedium(
                    text = stringResource(id = R.string.sign_up),
                    color = Color.White
                )
            }

            TextButton(
                onClick = {
                    navController.navigate(Screen.SignIn.route)
                }
            ) {
                TextMedium(
                    text = stringResource(id = R.string.sign_in),
                    color = PrimaryRed50
                )
            }
        }
    }
}