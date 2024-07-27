package com.example.meritmatch.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
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
import com.example.meritmatch.ui.theme.LoginScreenColor
import com.example.meritmatch.ui.theme.michroma

@Composable
fun LandingPage(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.homepagebackground),
            contentDescription = "Home page background",
            modifier = Modifier.fillMaxSize(

            )
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.Black.copy(alpha = 0.8f),
            ),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //title text
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "MERIT",
                color = LoginScreenColor,
                textAlign = TextAlign.Center,
                fontSize = 80.sp,
                fontFamily = michroma
            )
            Text(
                text = "MATCH",
                color = LoginScreenColor,
                textAlign = TextAlign.Center,
                fontSize = 65.sp,
                fontFamily = michroma
            )
        }

        //buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //sign up
            Button(
                onClick = {
                    navController.navigate(Screens.SignUpPage.route)
                },
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .padding(5.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = HomeScreenButtonColor.copy(
                        alpha = 0.5f
                    )
                )
            ) {
                Text(
                    text = "Sign Up",
                    fontFamily = michroma,
                    fontSize = 30.sp,
                    color = LoginScreenColor
                )
            }
            //login
            Button(
                onClick = {
                    navController.navigate(Screens.LoginPage.route)
                },
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .padding(5.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = HomeScreenButtonColor.copy(
                        alpha = 0.5f
                    )
                )
            ) {
                Text(
                    text = "Login",
                    fontFamily = michroma,
                    fontSize = 30.sp,
                    color = LoginScreenColor
                )
            }
            // TODO: browse option for case where sign in is not reqd
//            Button(
//                onClick = { },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color.Transparent
//                ),
//                modifier = Modifier.padding(top = 10.dp)
//            ) {
//                Text(
//                    text = "Browse",
//                    fontFamily = michroma,
//                    fontSize = 45.sp,
//                    color = LoginScreenColor
//                )
//            }
        }
    }
}

@Preview
@Composable
private fun HomePagePreview() {
    LandingPage(navController = rememberNavController())
}