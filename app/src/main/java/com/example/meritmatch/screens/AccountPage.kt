package com.example.meritmatch.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.meritmatch.MainViewModel
import com.example.meritmatch.R
import com.example.meritmatch.navigation.Screens
import com.example.meritmatch.ui.theme.HomeScreenColor
import com.example.meritmatch.ui.theme.archivoRegular

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AccountPage(navController: NavController, viewModel: MainViewModel) {
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
            BottomNavigationBar(navController = navController, viewModel = viewModel,)
        },
        containerColor = HomeScreenColor
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.profile_pic),
                contentDescription = "pfp"
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .consumeWindowInsets(it)) {
                viewModel.fetchUserTasks()
                LazyColumn(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                        .then(
                            Modifier.padding(start = 20.dp, end = 20.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    stickyHeader {

                    }

                    //current user
                    item {
                        Text(
                            text = viewModel.currentUser,
                            color = Color.White,
                            fontSize = 45.sp,
                            fontFamily = archivoRegular
                        )
                    }

                    //divider
                    item {
                        HorizontalDivider(
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp)
                                .fillMaxWidth(),
                            thickness = 2.dp
                        )
                    }

                    //spacer
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    //karma points
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
                                viewModel.fetchKarma()
                                Text(
                                    text = viewModel.karma.toString(),
                                    color = Color.White,
                                    fontSize = 25.sp,
                                    fontFamily = archivoRegular,
                                )
                            }
                        }
                    }
//
//                    item {
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(bottom = 10.dp),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.SpaceBetween
//                        ) {
//                            Text(
//                                text = "Tasks Completed: ",
//                                color = Color.White,
//                                fontSize = 25.sp,
//                                fontFamily = archivoRegular,
//                            )
//                            Text(
//                                text = "3",
//                                color = Color.White,
//                                fontSize = 25.sp,
//                                fontFamily = archivoRegular,
//                            )
//                        }
//                    }
                    //
                    //                item {
                    //                    Row(
                    //                        modifier = Modifier
                    //                            .fillMaxWidth()
                    //                            .padding(bottom = 10.dp),
                    //                        verticalAlignment = Alignment.CenterVertically,
                    //                        horizontalArrangement = Arrangement.SpaceBetween
                    //                    ) {
                    //                        Text(
                    //                            text = "Tasks Created: ",
                    //                            color = Color.White,
                    //                            fontSize = 25.sp,
                    //                            fontFamily = archivoRegular,
                    //                        )
                    //                        Text(
                    //                            text = "3",
                    //                            color = Color.White,
                    //                            fontSize = 25.sp,
                    //                            fontFamily = archivoRegular,
                    //                        )
                    //                    }
                    //                }

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

                    items(viewModel.reservedTasks) { taskItem ->
                        TaskBox(
                            title = taskItem.task_title,
                            content = taskItem.task_desc,
                            userName = taskItem.username,
                            karma = taskItem.karma,
                            navController = navController,
                            taskStatus = taskItem.task_status,
                            taskId = taskItem.taskid
                        )
                    }
                }
            }
        }
    }
}
