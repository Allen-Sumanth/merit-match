package com.example.meritmatch.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.meritmatch.MainViewModel
import com.example.meritmatch.navigation.Screens
import com.example.meritmatch.ui.theme.CardColor
import com.example.meritmatch.ui.theme.HomeScreenColor
import com.example.meritmatch.ui.theme.archivoRegular

@Composable
fun SettingsPage(navController: NavController, viewModel: MainViewModel) {
    BackHandler {//handles back button press
        navController.navigate(Screens.HomePage.route) {
            popUpTo(Screens.HomePage.route){
                inclusive = true
                saveState = true
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController = navController, viewModel = viewModel)
        },
        containerColor = HomeScreenColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(vertical = 5.dp)
            ) {
                Button(
                    onClick = {
                        viewModel.selectedNavBarIndex = 1
                        navController.navigate(Screens.LandingPage.route) {
                            popUpTo(Screens.LandingPage.route) {
                                inclusive = true
                                saveState = false
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(60.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CardColor.copy(0.28f)
                    )
                ) { }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "logout"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Logout",
                        fontFamily = archivoRegular,
                        fontSize = 25.sp,
                        color = Color.White
                    )
                }
            }

        }
    }
}
