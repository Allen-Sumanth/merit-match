package com.example.meritmatch.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.meritmatch.MainViewModel
import com.example.meritmatch.R
import com.example.meritmatch.backend.AccountBase
import com.example.meritmatch.navigation.Screens
import com.example.meritmatch.ui.theme.HomeScreenButtonColor
import com.example.meritmatch.ui.theme.LoginButtonColor
import com.example.meritmatch.ui.theme.LoginScreenColor
import com.example.meritmatch.ui.theme.archivoBold
import com.example.meritmatch.ui.theme.archivoRegular
import com.example.meritmatch.ui.theme.michroma
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun SignUpPage(navController: NavController, viewModel: MainViewModel) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        //background image
        Box(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(it)
        ) {
            Image(
                painter = painterResource(id = R.drawable.homepagebackground),
                contentDescription = "Home page background",
                modifier = Modifier.fillMaxSize(

                )
            )
        }

        //content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.Black.copy(alpha = 0.8f),
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //region sign up text
            Text(
                text = "Sign Up",
                color = LoginScreenColor,
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                fontFamily = michroma,
                modifier = Modifier.padding(bottom = 60.dp)
            )
            //endregion

            //region username field
            TextField(
                value = viewModel.currentUser,
                onValueChange = {
                    viewModel.currentUser = it
                    viewModel.verifyUserName(viewModel.currentUser)
                },
                singleLine = true,
                label = {
                    Text(
                        text = "Username",
                        color = Color.White.copy(alpha = 0.5f),
                        fontFamily = archivoRegular
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = HomeScreenButtonColor.copy(
                        alpha = 0.5f
                    ),
                    unfocusedContainerColor = HomeScreenButtonColor.copy(
                        alpha = 0.5f
                    ),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.padding(bottom = 20.dp)
            )
            //endregion

            //region password field
            var password by remember { mutableStateOf("") }
            TextField(
                value = password,
                onValueChange = {str ->
                    password = str

                },
                singleLine = true,
                label = {
                    Text(
                        text = "Password",
                        color = Color.White.copy(alpha = 0.5f),
                        fontFamily = archivoRegular
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = HomeScreenButtonColor.copy(
                        alpha = 0.5f
                    ),
                    unfocusedContainerColor = HomeScreenButtonColor.copy(
                        alpha = 0.5f
                    ),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier.padding(bottom = 40.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )
            //endregion

            //region create account button
            Button(
                onClick = {
                    viewModel.verifyUserName(viewModel.currentUser)
                    if (viewModel.isNewUser && password.isNotBlank() && viewModel.currentUser.isNotBlank()) {
                        val accountDetails = AccountBase(
                            password = password,
                            username = viewModel.currentUser,
                            karma = 1500
                        )
                        runBlocking {
                            val accountCreationSuccess = viewModel.createNewAccount(accountDetails)
                            if (accountCreationSuccess) {
                                viewModel.karma = 1500
                                navController.navigate(Screens.HomePage.route)
                            }
                        }

                    } else if (password.isBlank() || viewModel.currentUser.isBlank()) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Enter valid username and password",
                                duration = SnackbarDuration.Short
                            )
                        }
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Username already exists",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .height(65.dp)
                    .width(250.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LoginButtonColor.copy(0.5f)
                )
            ) {
                Text(
                    text = "Create Account",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontFamily = archivoBold
                )
            }
            //endregion

            //region login page button
            Button(
                onClick = {
                    viewModel.currentUser = ""
                    navController.navigate(Screens.LoginPage.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Text(
                    text = "Already a user? Log In",
                    fontFamily = michroma,
                    fontSize = 16.sp,
                    color = LoginScreenColor
                )
            }
            //endregion
        }
    }

}