package com.example.meritmatch.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.meritmatch.MainViewModel
import com.example.meritmatch.backend.NewTaskBase
import com.example.meritmatch.ui.theme.CardColor
import com.example.meritmatch.ui.theme.HomeScreenColor
import com.example.meritmatch.ui.theme.SubmitButtonColor
import com.example.meritmatch.ui.theme.archivoRegular
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun NewTaskPage(navController: NavController, viewModel: MainViewModel) {
    var taskTitle by remember {
        mutableStateOf("")
    }
    var karma by remember {
        mutableStateOf("")
    }
    var taskDesc by remember {
        mutableStateOf("")
    }

    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController = navController, viewModel = viewModel,)
        },
        containerColor = HomeScreenColor
    ) { it ->
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
                .then(Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //char limit
            val charLimit = 999
            val focusManager = LocalFocusManager.current
            //task title
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                value = taskTitle,
                onValueChange = {
                    taskTitle = it
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedTextColor = Color.LightGray,
                    focusedTextColor = Color.LightGray,
                    focusedSupportingTextColor = Color.LightGray
                ),
                placeholder = {
                    Text(
                        text = "Job Title",
                        fontFamily = archivoRegular,
                        color = Color.Gray,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                },
                maxLines = 1,
                textStyle = TextStyle.Default.copy(fontSize = 30.sp),
            )

            //karma points
            Row {
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(top = 30.dp)
                ) {
                    Text(
                        text = "Karma Points:",
                        fontFamily = archivoRegular,
                        color = Color.White,
                        fontSize = 30.sp
                    )
                    Text(
                        text = "Available: ${viewModel.karma}",
                        fontFamily = archivoRegular,
                        color = Color.Gray,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Right
                    )
                }

                Spacer(modifier = Modifier.width(40.dp))

                TextField(
                    value = karma,
                    onValueChange = {
                        if (it.isDigitsOnly()) {
                            karma = it
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedTextColor = Color.LightGray,
                        focusedTextColor = Color.LightGray,
                    ),
                )
            }

            //task description
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(top = 30.dp)
                ) {
                    Text(
                        text = "Description:",
                        fontFamily = archivoRegular,
                        color = Color.White,
                        fontSize = 30.sp
                    )
                    Text(
                        text = "Limit: 1000 characters",
                        fontFamily = archivoRegular,
                        color = Color.Gray,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Right
                    )
                }
            }
            TextField(
                value = taskDesc,
                onValueChange = {
                    taskDesc = it.take(charLimit)
                    if (it.length > charLimit) {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                minLines = 20,
                maxLines = 20,
                shape = RoundedCornerShape(15.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = CardColor.copy(alpha = 0.28f),
                    focusedContainerColor = CardColor.copy(alpha = 0.28f),
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                supportingText = {
                    Text(text = "${taskDesc.length} / 1000", textAlign = TextAlign.End)
                }
            )

            //submit button
            Button(
                onClick = {
                    if (viewModel.karma < karma.toInt()){
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "insufficient karma points",
                                withDismissAction = true
                            )
                        }
                    }
                    else if (taskTitle.isNotBlank() && karma.isNotBlank() && taskDesc.isNotBlank()) {
                        val time = Calendar.getInstance().time
                        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        val currentDate = formatter.format(time)
                        val newTask =  NewTaskBase(
                            assign_date = currentDate,
                            karma = karma.toInt(),
                            task_desc = taskDesc,
                            task_status = "NEW",
                            task_title = taskTitle,
                            username = viewModel.currentUser
                        )
                        viewModel.addNewTask(newTask)
                        navController.navigateUp()
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "please fill out all fields",
                            )
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = SubmitButtonColor
                ),
                modifier = Modifier.padding(30.dp)
            ) {
                Text(
                    text = "Submit",
                    fontFamily = archivoRegular,
                    color = Color.Black,
                    fontSize = 30.sp
                )
            }
            
            Spacer(modifier = Modifier.height(500.dp))
        }
    }
}