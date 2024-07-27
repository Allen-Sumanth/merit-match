package com.example.meritmatch.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meritmatch.MainViewModel
import com.example.meritmatch.R
import com.example.meritmatch.ui.theme.HomeScreenColor
import com.example.meritmatch.ui.theme.NavBarColor
import com.example.meritmatch.ui.theme.archivoRegular

@Composable
fun AccountPage(navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(viewModel = viewModel)
        },
        containerColor = HomeScreenColor
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .then(
                    Modifier.padding(start = 20.dp, end = 20.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.profile_pic),
                    contentDescription = "pfp"
                )
            }

            item {
                Text(
                    text = "MyUserName",
                    color = Color.White,
                    fontSize = 45.sp,
                    fontFamily = archivoRegular
                )
            }
            item {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth(),
                    thickness = 2.dp
                )
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total Karma: ",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontFamily = archivoRegular,
                    )
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.star_karma),
                            contentDescription = "pause symbol",
                            modifier = Modifier
                                .size(25.dp)
                                .padding(end = 5.dp)
                        )
                        Text(
                            text = "550",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontFamily = archivoRegular,
                        )
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Tasks Completed: ",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontFamily = archivoRegular,
                    )
                    Text(
                        text = "3",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontFamily = archivoRegular,
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Tasks Created: ",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontFamily = archivoRegular,
                    )
                    Text(
                        text = "3",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontFamily = archivoRegular,
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                Text(
                    text = "My Tasks",
                    color = Color.White,
                    fontSize = 45.sp,
                    fontFamily = archivoRegular,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth(),
                    thickness = 2.dp
                )
            }

            item { 
                TaskBox()
            }
        }
    }
}

@Preview
@Composable
private fun AccountPageDiscontinued() {
    AccountPage(navController = rememberNavController(), viewModel = MainViewModel())
}