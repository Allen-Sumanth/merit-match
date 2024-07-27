package com.example.meritmatch.screens

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.meritmatch.MainViewModel
import com.example.meritmatch.ui.theme.CardColor
import com.example.meritmatch.ui.theme.HomeScreenColor
import com.example.meritmatch.ui.theme.NavBarColor
import com.example.meritmatch.ui.theme.SubmitButtonColor
import com.example.meritmatch.ui.theme.archivoRegular

@Composable
fun NewTaskPage(navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(viewModel = viewModel)
        },
        containerColor = HomeScreenColor
    ) { it ->
        Column(
            modifier = Modifier
                .padding(it)
                .then(Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Text(
//                text = "New Task",
//                color = Color.White,
//                fontSize = 45.sp,
//                fontFamily = archivoRegular
//            )
//            HorizontalDivider(
//                modifier = Modifier
//                    .padding(top = 10.dp, bottom = 10.dp)
//                    .fillMaxWidth(),
//                thickness = 2.dp
//            )
            var title = ""
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                value = title,
                onValueChange = {
                    title = it
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedTextColor = Color.LightGray,
                    focusedTextColor = Color.LightGray,
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
            )

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
                        text = "Available: 550",
                        fontFamily = archivoRegular,
                        color = Color.Gray,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Right
                    )
                }

                Spacer(modifier = Modifier.width(40.dp))

                // TODO: add proper values
                var karma = ""
                TextField(
                    value = karma,
                    onValueChange = {
                        karma = it
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

            var description = ""
            TextField(
                value = description,
                onValueChange = {
                    description = it
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
                )
            )

            Button(
                onClick = { /*TODO*/ },
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
        }
    }
}

@Preview
@Composable
private fun NewTaskPreview() {
    NewTaskPage(navController = rememberNavController(), viewModel = MainViewModel())
}