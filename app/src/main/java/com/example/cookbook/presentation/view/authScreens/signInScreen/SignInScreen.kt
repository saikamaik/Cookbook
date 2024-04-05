package com.example.cookbook.presentation.view.authScreens.signInScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.cookbook.data.model.Response
import com.example.cookbook.navigation.Screen
import com.example.cookbook.presentation.view.authScreens.signInScreen.signInUiEvent.SignInUiEvent
import com.example.cookbook.presentation.view.common.ErrorText
import com.example.cookbook.presentation.view.common.TextMedium
import com.example.cookbook.ui.theme.NoRippleTheme
import com.example.cookbook.ui.theme.PrimaryRed50
import com.example.cookbook.ui.theme.TertiaryGray30
import com.example.cookbook.ui.theme.Typography
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    navController: NavHostController,
    @ApplicationContext context: Context
) {

    val coroutineScope = rememberCoroutineScope()

    val viewModel: SignInViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ) {

        Text(
            text = stringResource(id = R.string.sign_in),
            style = Typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = PrimaryRed50,
            modifier = Modifier
                .padding(bottom = 18.dp)
                .align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = uiState.value.emailTextFieldValue,
            onValueChange = {
                viewModel.postUiEvent(SignInUiEvent.ChangeEmailTextValue(it))
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
            value = uiState.value.passwordTextFieldValue,
            onValueChange = {
                viewModel.postUiEvent(SignInUiEvent.ChangePasswordTextValue(it))
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
                        viewModel.postUiEvent(SignInUiEvent.ChangePasswordVisibility)
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
                    if (viewModel.emailAndPasswordValidation(
                            uiState.value.emailTextFieldValue.trim(),
                            uiState.value.passwordTextFieldValue
                        )
                    ) {
                        coroutineScope.launch(Dispatchers.Main) {
                            viewModel.signInWithEmail(
                                uiState.value.emailTextFieldValue.trim(),
                                uiState.value.passwordTextFieldValue
                            ).collect {
                                when (it) {
                                    is Response.Success -> {
                                        viewModel.postUiEvent(SignInUiEvent.ChangeErrorStatus(false))
                                        navController.navigate(Screen.Home.route) {
                                            popUpTo(Screen.SignIn.route) {
                                                inclusive = true
                                            }
                                        }
                                    }

                                    is Response.Failure -> {
                                        Toast.makeText(
                                            context,
                                            it.e,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                    is Response.Loading -> {
                                        Toast.makeText(
                                            context,
                                            getString(context, R.string.loading),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }
                    } else {
                        Toast.makeText(
                            context,
                            getString(context, R.string.enter_valid_cred),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryRed50
                ),
                shape = RoundedCornerShape(20),
                modifier = Modifier.padding(bottom = 6.dp, top = 6.dp)
            ) {
                TextMedium(
                    text = stringResource(id = R.string.sign_in),
                    color = Color.White
                )
            }

            TextButton(
                onClick = {
                    navController.navigate(Screen.SignUp.route)
                },
                modifier = Modifier.padding(bottom = 6.dp, top = 6.dp)
            ) {
                TextMedium(
                    text = stringResource(id = R.string.sign_up),
                    color = PrimaryRed50
                )
            }
        }
    }
}