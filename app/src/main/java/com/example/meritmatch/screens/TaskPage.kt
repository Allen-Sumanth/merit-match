package com.example.meritmatch.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.meritmatch.ui.theme.CardColor
import com.example.meritmatch.ui.theme.archivoRegular

@Composable
fun TaskPage(navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(viewModel = viewModel)
        },
        containerColor = HomeScreenColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 30.dp, start = 20.dp, end = 20.dp)
                    .fillMaxSize()
            ) {
                Column(modifier = Modifier.padding(bottom = 20.dp)) {
                    Text(
                        text = "RUser123",
                        color = Color.White,
                        fontSize = 40.sp,
                        fontFamily = archivoRegular
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 10.dp)
                            .fillMaxWidth(0.9f),
                        thickness = 2.dp
                    )
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.star_karma),
                            contentDescription = "pause symbol",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "550",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontFamily = archivoRegular
                        )
                    }
                }

                Card(
                    onClick = {},
                    colors = CardDefaults.cardColors(
                        containerColor = CardColor.copy(0.28f),
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 25.dp),
                ) {
                    Text(
                        text = "iwfewiufwiufw",
                        modifier = Modifier.padding(10.dp),
                        fontSize = 20.sp,
                        fontFamily = archivoRegular
                    )
                }

                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    if(viewModel.taskActive) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(vertical = 5.dp)
                        ) {
                            Button(
                                onClick = { /*TODO*/ },
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
                                Image(
                                    painter = painterResource(id = R.drawable.green_tick),
                                    contentDescription = "completed",
                                    modifier = Modifier.size(35.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Mark as completed",
                                    fontFamily = archivoRegular,
                                    fontSize = 25.sp,
                                    color = Color.White
                                )
                            }
                        }

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(vertical = 5.dp)
                        ) {
                            Button(
                                { /*TODO*/ },
                                Modifier
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
                                Image(
                                    painter = painterResource(id = R.drawable.red_cross),
                                    contentDescription = "unreserve",
                                    modifier = Modifier.size(45.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Unreserve task",
                                    fontFamily = archivoRegular,
                                    fontSize = 25.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                    else {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(vertical = 5.dp)
                        ) {
                            Button(
                                onClick = { /*TODO*/ },
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
                                Text(
                                    text = "Reserve Task",
                                    fontFamily = archivoRegular,
                                    fontSize = 25.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun TaskPagePreview() {
    TaskPage(navController = rememberNavController(), viewModel = MainViewModel())
}