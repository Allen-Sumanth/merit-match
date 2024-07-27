package com.example.meritmatch.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meritmatch.R
import com.example.meritmatch.navigation.Screens
import com.example.meritmatch.ui.theme.HomeScreenButtonColor
import com.example.meritmatch.ui.theme.LoginButtonColor
import com.example.meritmatch.ui.theme.LoginScreenColor
import com.example.meritmatch.ui.theme.archivoBold
import com.example.meritmatch.ui.theme.archivoRegular
import com.example.meritmatch.ui.theme.michroma

@Composable
fun LoginPage(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.homepagebackground),
            contentDescription = "Home page background",
            modifier = Modifier.fillMaxSize()
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.Black.copy(alpha = 0.8f),
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = "Login",
            color = LoginScreenColor,
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            fontFamily = michroma,
            modifier = Modifier.padding(bottom = 60.dp)
        )

        var userName = ""
        TextField(
            value = userName,
            onValueChange = {
                userName = it
            },
            singleLine = true,
            label = {
                Text(
                    text = "Username",
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 20.sp,
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

        var password = ""
        TextField(
            value = password,
            onValueChange = {
                userName = it
            },
            singleLine = true,
            label = {
                Text(
                    text = "Password",
                    color = Color.White.copy(alpha = 0.5f),
                    fontSize = 20.sp,
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
            modifier = Modifier.padding(bottom = 40.dp)
        )

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.height(65.dp).width(250.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = LoginButtonColor.copy(0.5f)
            )
        ) {
            Text(text = "Login",
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = archivoBold
            )
        }
        Button(
            onClick = {
                navController.navigate(Screens.SignUpPage.route)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text(
                text = "New user? Sign Up",
                fontFamily = michroma,
                fontSize = 16.sp,
                color = LoginScreenColor
            )
        }
    }
}

@Preview
@Composable
private fun LoginPrev() {
    LoginPage(navController = rememberNavController())
}