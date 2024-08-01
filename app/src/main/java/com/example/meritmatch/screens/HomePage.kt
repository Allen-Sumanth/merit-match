package com.example.meritmatch.screens

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.meritmatch.MainViewModel
import com.example.meritmatch.R
import com.example.meritmatch.navigation.Screens
import com.example.meritmatch.ui.theme.CardColor
import com.example.meritmatch.ui.theme.HomeScreenColor
import com.example.meritmatch.ui.theme.NewTaskColor
import com.example.meritmatch.ui.theme.archivoBold
import com.example.meritmatch.ui.theme.archivoRegular

@SuppressLint("MutableCollectionMutableState")
@Composable
fun HomePage(navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController = navController, viewModel = viewModel,)
        },
        containerColor = HomeScreenColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            //page content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 30.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
                verticalArrangement = Arrangement.Top
            ) {
                //title
                Column {
                    Text(
                        text = "Reserved Tasks",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontFamily = archivoRegular
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 25.dp)
                            .fillMaxWidth(0.9f),
                        thickness = 2.dp
                    )
                }

                //reserved tasks
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    viewModel.fetchReservedTasks()
                    LazyColumn() {
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

                //available tasks
                Column(
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Text(
                        text = "Available Tasks",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontFamily = archivoRegular
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 25.dp)
                            .fillMaxWidth(0.9f),
                        thickness = 2.dp
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
//                    .background(color = Color.Blue)
                ) {
                    viewModel.fetchNewTasks()
                    LazyColumn {
                        items(viewModel.newTasks) { taskItem ->
                            TaskBox(
                                title = taskItem.task_title,
                                content = taskItem.task_desc,
                                userName = taskItem.username,
                                karma = taskItem.karma,
                                taskStatus = taskItem.task_status,
                                navController = navController,
                                taskId = taskItem.taskid
                            )
                        }
                    }
                }
            }

            //new task button
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Button(
                        onClick = {
                            navController.navigate(Screens.NewTaskPage.route)
                        },
                        modifier = Modifier
                            .padding(10.dp)
                            .size(60.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        )
                    ) { }
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "newtask",
                        modifier = Modifier.size(40.dp),
                        tint = NewTaskColor
                    )
                }
            }
        }
    }
}

@Composable
fun TaskBox(
    title: String,
    content: String,
    userName: String,
    karma: Int,
    taskStatus: String,
    navController: NavController,
    taskId: Int
) {
    Card(
        onClick = {
            navController.navigate(Screens.TaskPage.route + "/$karma"+"/$userName"+"/$content"+"/$taskStatus"+"/$taskId")
        },
        colors = CardDefaults.cardColors(
            containerColor = CardColor.copy(0.28f),
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            //task title
            Text(
                text = title,
                fontFamily = archivoBold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 5.dp)
            )

            //task content
            Text(
                text = content,
                fontFamily = archivoRegular,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(10.dp))

            //username and karma
            Row {
                Text(text = userName, fontFamily = archivoBold)
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.star_karma),
                    contentDescription = "pause symbol",
                    modifier = Modifier.size(15.dp)
                )
                Text(text = karma.toString())
            }
        }
    }
}
